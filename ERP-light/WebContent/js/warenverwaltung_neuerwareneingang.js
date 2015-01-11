//load page in specific mode
$(document).ready(function() {
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null){
	       return null;
	    }
	    else{
	       return results[1] || 0;
	    }
	}
	
	var mode = $.urlParam('mode');
	var id = $.urlParam('id');
	
	if(mode == "new"){
		$("#tabtext").text("Neuer Wareneingang");
	}
	else if(mode == "edit"){
		$("#tabtext").text("Bearbeite Wareneingang");
		loadNewIncomingDelivery(id);
	}
});

function loadNewIncomingDelivery(id){
	var inc;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/incomingDelivery/getById/" + id
	}).done(function(data) {
			inc = eval(data);
	});
	
	//get organisation by id
	var org;
	var delivererString;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/organisation/getOrganisationById/" + inc.organisationId
	}).done(function(data) {
		org = eval(data);
		delivererString = org.name + ", " + org.country + ", " + org.zip + " " + org.city;
		$("#tbx_deliverer").val(delivererString);
	});
	
	$("#tbx_date").val(inc.date);
	$("#tbx_comment").val(inc.comment);
					
	//get articles
	var article = inc.incomingArticleDTOs;
	for(var i=0; i < article.length; i++){
		var articleId = article[i].articleDTO.articleId;
		var description = article[i].articleDTO.description;
		var numberpu = article[i].numberpu;
		var packagingUnit = article[i].articleDTO.packagingUnit;
		var weightpu = article[i].articleDTO.weightpu;
		var mdd = article[i].articleDTO.mdd;
		var pricepu = parseFloat(article[i].articleDTO.pricepu);
		
		//calculate sum price
		var sum = pricepu * article[i].numberpu;
		
		var tableRow = "<tr id='"+articleId+"'>" + "<td>" + articleId
		+ "</td>" + "<td>" + description
		+ "</td>" + "<td>" + numberpu
		+ "</td>" + "<td>" + packagingUnit
		+ "</td>" + "<td>" + weightpu
		+ "</td>" + "<td>" + mdd
		+ "</td>" + "<td>" + pricepu + " €"
		+ "</td>" + "<td>" + sum + " €"
		+ "</td>" + "</tr>";
		
		$("#newIncomingDeliveryTableBody").append(tableRow);
	}
}

//loads all deliverers
function loadAllDeliverers(id) {
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/organisation/getAllOrganisations"
	}).done(
			function(data) {
				var o = eval(data);
				
				for (var e in o) {
					for(var i=0; i< o[e].types.length; i++){
						if(o[e].types[i] == "Lieferant"){
							var o_divRow = "<div class='boxElement_deliverer'>" + "<input type='hidden' value="+ o[e].id +">" + "<span>" + o[e].name + " "
							+ "</span><input class='pull-right' value="+ o[e].id +" id="+ o[e].id +" name='delivererRadio' type='radio'></div>";
							
							$("#delivererDiv").append(o_divRow);
						}
					}
				}
	});
};

//load deliverer modal
$("#btn_addDeliverer").click(function() {
	$(".boxElement_deliverer").remove();
	$("#filter_modal").val("");
	loadAllDeliverers();
});

function createTableRow(count){
	var pricepu = parseFloat($("#tbx_pricepackagingunit").val());
	var sum = pricepu * $("#tbx_numberofpackagingunits").val();
	
	var tableRow = "<tr id="+ count +">" + "<td>" + count
	+ "</td>" + "<td>" + $("#tbx_description").val()
	+ "</td>" + "<td>" + $("#tbx_numberofpackagingunits").val()
	+ "</td>" + "<td>" + $("#tbx_packagingunit").val()
	+ "</td>" + "<td>" + $("#tbx_weightpackagingunit").val()
	+ "</td>" + "<td>" + $("#tbx_mdd").val()
	+ "</td>" + "<td>" + $("#tbx_pricepackagingunit").val() + " €"
	+ "</td>" + "<td>" + sum + " €" //TODO Gesamtpreis
	+ "</td>" + "</tr>";
	
	return tableRow;
}

//TODO submit to depot
$("#btn_submittodepot").click(function() {
	var newincomingdelivery = new Object();
	
	
	
	$.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "POST",
		url : "../rest/secure/incomingDelivery/set",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",
		data : JSON.stringify(newincomingdelivery)
	}).done(function(data) {
		if (data) {
			location.href="warenverwaltung_wareneingang.html";
			
			if (data.success == true)
			{
				showAlertElement(1, data.message, 5000);
			}
			else
			{
				showAlertElement(2, data.message, 5000);
			}
		} 
		else {
			alert("Verbindungsproblem mit dem Server");
		}
	});
});

//saves an article to the table
var articleCount = 1;
$("#btn_savearticle").click(function() {
	if($("#modal_title_text").text() == "Neue Position"){
		var rowTemplate = createTableRow(articleCount);
		$("#newIncomingDeliveryTableBody").append(rowTemplate);
		articleCount++;
	}
	else{
		var id = tableData[0];
		var rowTemplate = createTableRow(id);
		$("#" + id).remove();
		$("#newIncomingDeliveryTableBody").append(rowTemplate);
	}
	
	$('#new').modal('hide');
});

//save deliverer to textbox
$("#btn_saveDeliverer").click(function() {
	//get Id of checked radiobox of deliverer div
	var id = $("#delivererDiv input[name='delivererRadio']:checked").val();
	
	var delivererString;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/organisation/getOrganisationById/" + id
	}).done(
			function(data) {
				var o = eval(data);
				delivererString = o.name + ", " + o.country + ", " + o.zip + " " + o.city;
	});
	
	$("#tbx_deliverer").val(delivererString);
	$('#chooseDelivererModal').modal('hide');
});

//new position modal
$("#btn_new").click(function() {
	$("#modal_title_text").text("Neue Position");
	clearPositionModal();
});

//move marked row one row upwards
$("#btn_up").click(function() {
	var id = tableData[0];
	
	var thisRow = $("#" + id).closest('tr');
    var prevRow = thisRow.prev();
    if (prevRow.length) {
        prevRow.before(thisRow);
    }
});

//move marked row one row downwards
$("#btn_down").click(function() {
	var id = tableData[0];
	
	var thisRow = $("#" + id).closest('tr');
    var nextRow = thisRow.next();
    if (nextRow.length) {
        nextRow.after(thisRow);
    }
});

//TODO Load selected article to modal
$("#btn_edit").click(function() {
	$("#modal_title_text").text("Bearbeite Position");
	clearPositionModal();
	
	$("#tbx_description").val(tableData[1]);
	$("#tbx_numberofpackagingunits").val(tableData[2]);
	$("#tbx_packagingunit").val(tableData[3]);
	$("#tbx_weightpackagingunit").val(tableData[4]);
	$("#tbx_mdd").val(tableData[5]);
	$("#tbx_pricepackagingunit").val(tableData[6]);
});

//clears position modal textboxes
function clearPositionModal(){
	$("#tbx_description").val("");
	$("#tbx_numberofpackagingunits").val("");
	$("#tbx_packagingunit").val("");
	$("#tbx_weightpackagingunit").val("");
	$("#tbx_mdd").val("");
	$("#tbx_pricepackagingunit").val("");
}

/**
 * search filter for deliverer
 */
$(document).ready(function() {
	(function($) {
		$('#filter_modal').keyup(function() {

			var rex = new RegExp($(this).val(), 'i');
			$('.searchable .boxElement_deliverer').hide();
			$('.searchable .boxElement_deliverer').filter(function() {
				return rex.test($(this).text());
			}).show();
		})
	}(jQuery));
});

//disable new, edit and delete buttons
$('#btn_new').hide();
$(".suchfilter").css("margin-left", "0px");
$('#btn_edit').hide();
$('#btn_deleteModal').hide();

// get current user rights
$(document).ready(function() {
	$.ajax({
		type : "POST",
		url : "../rest/secure/person/getCurrentUser"
	}).done(function(data) {
		currentUser = eval(data);
		currentUserRights = currentUser.permission;

		// only when user has admin rights
		if (currentUserRights == "Admin" && currentUserRights != "") {
			$("#btn_new").show();
			$(".suchfilter").css("margin-left", "5px");

			$('#btn_edit').show();
			$('#btn_deleteModal').show();

			$('#btn_up').prop('disabled', true);
			$('#btn_down').prop('disabled', true);
			$('#btn_edit').prop('disabled', true);
			$('#btn_deleteModal').prop('disabled', true);
		}
	});
});

var tableData;
$('#TableHead').on('click','tbody tr', function(event) {
			tableData = $(this).children("td").map(function() {
				return $(this).text();
			}).get();

			$(this).addClass('highlight').siblings().removeClass('highlight');

			// only when user has admin rights
			if (currentUserRights == "Admin" && currentUserRights != "") {
				$('#btn_up').prop('disabled', false);
				$('#btn_down').prop('disabled', false);
				$('#btn_edit').prop('disabled', false);
				$('#btn_deleteModal').prop('disabled', false);
			} 
			else {
				$('#btn_up').prop('disabled', true);
				$('#btn_down').prop('disabled', true);
				$('#btn_edit').prop('disabled', true);
				$('#btn_deleteModal').prop('disabled', true);
			}
});

/**
 * call the delete modal for the selected article
 */
$("#btn_deleteModal").click(function() {
	var name = tableData[1];
	$("#label_name_delete").text(name);
});


/**
 * remove selected table row
 */
$("#btn_deleteArticle").click(function() {
	var id = tableData[0];
	$("#" + id).remove();
	$('#deleteModal').modal('hide');
});
