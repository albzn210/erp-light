//load page in specific mode
var global_id;
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
	global_id = $.urlParam('id');
	
	if(mode == "new"){
		$("#tabtext").text("Neue Disposition");
		loadAllIncomingDeliveries();
	}
	else if(mode == "edit"){
		$("#tabtext").text("Bearbeite Disposition");
		loadTableContent(global_id);
	}
});

function loadAllIncomingDeliveries(){
	var inc;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/incomingDelivery/getAll"
	}).done(function(data) {
			inc = eval(data);
	});
	
	for (var e in inc) {
		//get articles (depot)
		var article = inc[e].incomingArticleDTOs;
		for(var i=0; i < article.length; i++){
			var articleId = article[i].articleDTO.articleId;
			var description = article[i].articleDTO.description;
			var numberpu = article[i].numberpu;
			var packagingUnit = article[i].articleDTO.packagingUnit;
			var weightpu = article[i].articleDTO.weightpu;
			var mdd = article[i].articleDTO.mdd;
			
			var tableRow = "<tr id='"+articleId+"'>" + "<td>" + articleId
			+ "</td>" + "<td class='article_description'>" + description
			+ "</td>" + "<td class='article_packagingunits'>" + numberpu
			+ "</td>" + "<td class='article_packaging_unit'>" + packagingUnit
			+ "</td>" + "<td class='article_mdd'>" + mdd
			+ "</td>" + "</tr>";
			
			$("#leftDepotTableBody").append(tableRow);
		}
	}
}

function loadTableContent(id){
	var out;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/outgoingDelivery/getById/" + id
	}).done(function(data) {
			out = eval(data);
	});
	
	//get organisation by id
	var org;
	var receiverString;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/organisation/getOrganisationById/" + out.organisationId
	}).done(function(data) {
		org = eval(data);
		receiverString = org.name + ", " + org.country + ", " + org.zip + " " + org.city;
		$("#tbx_receiver").val(receiverString);
	});
	
	$("#tbx_date").val(out.date);
	$("#tbx_comment").val(out.comment);
					
	//get articles (disposition)
	var article = out.outgoingArticleDTOs;
	for(var i=0; i < article.length; i++){
		var articleId = article[i].articleDTO.articleId;
		var description = article[i].articleDTO.description;
		var numberpu = article[i].numberpu;
		var packagingUnit = article[i].articleDTO.packagingUnit;
		var weightpu = article[i].articleDTO.weightpu;
		var mdd = article[i].articleDTO.mdd;
		
		var tableRow = "<tr id='"+articleId + "_new" +"'>" + "<td>" + articleId
		+ "</td>" + "<td class='article_description'>" + description
		+ "</td>" + "<td class='article_packagingunits'>" + numberpu
		+ "</td>" + "<td class='article_packaging_unit'>" + packagingUnit
		+ "</td>" + "<td class='article_mdd'>" + mdd
		+ "</td>" + "</tr>";
		
		$("#rightDepotTableBody").append(tableRow);
	}
	
	var inc;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/incomingDelivery/getAll"
	}).done(function(data) {
			inc = eval(data);
	});
	
	for (var e in inc) {
		//get articles (depot)
		var article = inc[e].incomingArticleDTOs;
		for(var i=0; i < article.length; i++){
			var articleId = article[i].articleDTO.articleId;
			var description = article[i].articleDTO.description;
			var numberpu = article[i].numberpu;
			var packagingUnit = article[i].articleDTO.packagingUnit;
			var weightpu = article[i].articleDTO.weightpu;
			var mdd = article[i].articleDTO.mdd;
			
			var tableRow = "<tr id='"+articleId+"'>" + "<td>" + articleId
			+ "</td>" + "<td class='article_description'>" + description
			+ "</td>" + "<td class='article_packagingunits'>" + numberpu
			+ "</td>" + "<td class='article_packaging_unit'>" + packagingUnit
			+ "</td>" + "<td class='article_mdd'>" + mdd
			+ "</td>" + "</tr>";
			
			$("#leftDepotTableBody").append(tableRow);
		}
	}
}

//loads all receiver
function loadAllReceivers() {
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/organisation/getAllOrganisations"
	}).done(
			function(data) {
				var o = eval(data);
				
				for (var e in o) {
					for(var i=0; i< o[e].types.length; i++){
						if(o[e].types[i] == "Kunde"){
							var o_divRow = "<div class='boxElement_receiver'>" + "<input type='hidden' value="+ o[e].id +">" + "<span>" + o[e].name + " "
							+ "</span><input class='pull-right' value="+ o[e].id +" name='receiverRadio' type='radio'></div>";
							
							$("#receiverDiv").append(o_divRow);
						}
					}
				}
	});
};

//create table row
function createTableRow(id, mode, article_description, article_packagingunits, article_packaging_unit, article_mdd){
	var id_new;
	if(mode == 0){
		id_new = id + "_new";
	}
	else if(mode == 1){
		id_new = id;
	}
	
	var tableRow = "<tr id='"+ id_new +"'>" + "<td>" + id
		+ "</td>" + "<td class='article_description'>" + article_description
		+ "</td>" + "<td class='article_packagingunits'>" + article_packagingunits
		+ "</td>" + "<td class='article_packaging_unit'>" + article_packaging_unit
		+ "</td>" + "<td class='article_mdd'>" + article_mdd
		+ "</td>" + "</tr>";
	
	return tableRow;
}

//load receiver modal
$("#btn_addReceiver").click(function() {
	$(".boxElement_receiver").remove();
	$("#filter_modal").val("");
	loadAllReceivers();
});

//save deliverer to textbox
$("#btn_saveReceiver").click(function() {
	//get Id of checked radiobox of deliverer div
	var id = $("#receiverDiv input[name='receiverRadio']:checked").val();
	
	var receiverString;
	$.ajax({
		type : "POST",
		async : false,
		url : "../rest/secure/organisation/getOrganisationById/" + id
	}).done(
			function(data) {
				var o = eval(data);
				receiverString = o.name + ", " + o.country + ", " + o.zip + " " + o.city;
	});
	
	$("#tbx_receiver").val(receiverString);
	$('#chooseReceiverModal').modal('hide');
});

//add article to disposition
$("#btn_addtodisposition").click(function() {
	var id = tableData[0];
	
	var thisRow = $("#" + id).closest('tr');
	var packagingunits_depot = $(thisRow).find(".article_packagingunits").html().trim();
	var packagingunits_tbx = $("#tbx_packagingunit").val().trim();
	
	var article_description = $(thisRow).find(".article_description").html();
	var article_packagingunits = parseFloat($(thisRow).find(".article_packagingunits").html()) - parseFloat(packagingunits_tbx);
	var article_packaging_unit = $(thisRow).find(".article_packaging_unit").html();
	var article_mdd = $(thisRow).find(".article_mdd").html();
	
	if($("#tbx_packagingunit").val() == ""){
		packagingunits_tbx = $(thisRow).find(".article_packagingunits").html();
		var newDispositionRow = createTableRow(id, 0, article_description, packagingunits_tbx, article_packaging_unit, article_mdd);
		
		$(thisRow).removeClass("highlight");
		$(thisRow).remove();
		$("#rightDepotTableBody").append(newDispositionRow);
	}
	else{
		if(parseFloat(packagingunits_tbx) > parseFloat(packagingunits_depot)){
			$("#tbx_packagingunit").val("");
			return false;
		}
		else{
			if(article_packagingunits == 0){
				$(thisRow).remove();
				$('#btn_addtodisposition').prop('disabled', true);
			}
			
			$(thisRow).find(".article_packagingunits").html(parseFloat(article_packagingunits));
			
			if($("#" + id + "_new").length != 0){
				var thisRow = $("#" + id + "_new").closest('tr');
				article_packagingunits = $(thisRow).find(".article_packagingunits").html();
				$(thisRow).find(".article_packagingunits").html(parseFloat(packagingunits_tbx) + parseFloat(article_packagingunits));
			}
			else{
				var newDispositionRow = createTableRow(id, 0, article_description, parseFloat(packagingunits_tbx), article_packaging_unit, article_mdd);
				$("#rightDepotTableBody").append(newDispositionRow);
			}
		}
	}
});

//remove article from disposition
$("#btn_removefromdisposition").click(function() {
	var id = tableData[0];
	
	var thisRow = $("#" + id + "_new").closest('tr');
	var packagingunits_depot = $(thisRow).find(".article_packagingunits").html().trim();
	var packagingunits_tbx = $("#tbx_packagingunit").val().trim();
	
	var article_description = $(thisRow).find(".article_description").html();
	var article_packagingunits = parseFloat($(thisRow).find(".article_packagingunits").html()) - parseFloat(packagingunits_tbx);
	var article_packaging_unit = $(thisRow).find(".article_packaging_unit").html();
	var article_mdd = $(thisRow).find(".article_mdd").html();
	
	if($("#tbx_packagingunit").val() == ""){
		packagingunits_tbx = $(thisRow).find(".article_packagingunits").html();
		var newDepotRow = createTableRow(id, 1, article_description, packagingunits_tbx, article_packaging_unit, article_mdd);
		
		$(thisRow).removeClass("highlight");
		$(thisRow).remove();
		$("#leftDepotTableBody").append(newDepotRow);
	}
	else{
		if(parseFloat(packagingunits_tbx) > parseFloat(packagingunits_depot)){
			$("#tbx_packagingunit").val("");
			return false;
		}
		else{
			if(article_packagingunits == 0){
				$(thisRow).remove();
				$('#btn_removefromdisposition').prop('disabled', true);
			}
			
			$(thisRow).find(".article_packagingunits").html(parseFloat(article_packagingunits));
			
			if($("#" + id).length != 0){
				var thisRow = $("#" + id).closest('tr');
				article_packagingunits = $(thisRow).find(".article_packagingunits").html();
				$(thisRow).find(".article_packagingunits").html(parseFloat(packagingunits_tbx) + parseFloat(article_packagingunits));
			}
			else{
				var newDepotRow = createTableRow(id, 1, article_description, parseFloat(packagingunits_tbx), article_packaging_unit, article_mdd);
				$("#leftDepotTableBody").append(newDepotRow);
			}
		}
	}
});

//close newoutgoing delivery and show outgoing delivery tab
$("#btn_close").click(function() {
	location.href="warenverwaltung_warenausgang.html";
	return false;
});

//move marked row one row upwards
$("#btn_up").click(function() {
	var id = tableData[0];
	
	var thisRow = $("#" + id + "_new").closest('tr');
    var prevRow = thisRow.prev();
    if (prevRow.length) {
        prevRow.before(thisRow);
    }
});

//move marked row one row downwards
$("#btn_down").click(function() {
	var id = tableData[0];
	
	var thisRow = $("#" + id + "_new").closest('tr');
    var nextRow = thisRow.next();
    if (nextRow.length) {
        nextRow.after(thisRow);
    }
});

/**
 * search filter for receiver
 */
$(document).ready(function() {
	(function($) {
		$('#filter_modal').keyup(function() {

			var rex = new RegExp($(this).val(), 'i');
			$('.searchable .boxElement_receiver').hide();
			$('.searchable .boxElement_receiver').filter(function() {
				return rex.test($(this).text());
			}).show();
		})
	}(jQuery));
});

//disable new, edit and delete buttons
$('#btn_addtodisposition').hide();
$('#btn_removefromdisposition').hide();

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
			$('#btn_addtodisposition').show();
			$('#btn_removefromdisposition').show();

			$('#btn_up').prop('disabled', true);
			$('#btn_down').prop('disabled', true);
			$('#btn_addtodisposition').prop('disabled', true);
			$('#btn_removefromdisposition').prop('disabled', true);
		}
	});
});

var tableData;
$('#TableHeadLeftDepot').on('click','tbody tr', function(event) {
			tableData = $(this).children("td").map(function() {
				return $(this).text();
			}).get();

			$(this).addClass('highlight').siblings().removeClass('highlight');

			// only when user has admin rights
			if (currentUserRights == "Admin" && currentUserRights != "") {
				$('#btn_addtodisposition').prop('disabled', false);
				$('#btn_removefromdisposition').prop('disabled', true);
			} 
			else {
				$('#btn_addtodisposition').prop('disabled', true);
				$('#btn_removefromdisposition').prop('disabled', true);
			}
});

$('#TableHeadRightDepot').on('click','tbody tr', function(event) {
	tableData = $(this).children("td").map(function() {
		return $(this).text();
	}).get();

	$(this).addClass('highlight').siblings().removeClass('highlight');

	// only when user has admin rights
	if (currentUserRights == "Admin" && currentUserRights != "") {
		$('#btn_up').prop('disabled', false);
		$('#btn_down').prop('disabled', false);
		$('#btn_addtodisposition').prop('disabled', true);
		$('#btn_removefromdisposition').prop('disabled', false);
	} 
	else {
		$('#btn_up').prop('disabled', true);
		$('#btn_down').prop('disabled', true);
		$('#btn_addtodisposition').prop('disabled', true);
		$('#btn_removefromdisposition').prop('disabled', true);
	}
});

