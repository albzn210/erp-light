package at.erp.light.view.model;

// Generated 22.11.2014 18:02:50 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Article generated by hbm2java
 */
@Entity
@Table(name = "article", schema = "public")
public class Article implements java.io.Serializable {

	private int articleId;
	private String description;
	private String packagingUnit;
	private double weightpu;
	private Date mdd;
	private double pricepu;
	private Set<OutgoingArticle> outgoingArticles = new HashSet<OutgoingArticle>(
			0);
	private Set<IncomingArticle> incomingArticles = new HashSet<IncomingArticle>(
			0);

	public Article() {
	}

	public Article(int articleId, String description, String packagingUnit,
			double weightpu, Date mdd, double pricepu) {
		this.articleId = articleId;
		this.description = description;
		this.packagingUnit = packagingUnit;
		this.weightpu = weightpu;
		this.mdd = mdd;
		this.pricepu = pricepu;
	}

	public Article(int articleId, String description, String packagingUnit,
			double weightpu, Date mdd, double pricepu,
			Set<OutgoingArticle> outgoingArticles,
			Set<IncomingArticle> incomingArticles) {
		this.articleId = articleId;
		this.description = description;
		this.packagingUnit = packagingUnit;
		this.weightpu = weightpu;
		this.mdd = mdd;
		this.pricepu = pricepu;
		this.outgoingArticles = outgoingArticles;
		this.incomingArticles = incomingArticles;
	}

	@Id
	@Column(name = "article_id", unique = true, nullable = false)
	public int getArticleId() {
		return this.articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	@Column(name = "description", nullable = false, length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "packaging_unit", nullable = false, length = 100)
	public String getPackagingUnit() {
		return this.packagingUnit;
	}

	public void setPackagingUnit(String packagingUnit) {
		this.packagingUnit = packagingUnit;
	}

	@Column(name = "weightpu", nullable = false, precision = 17, scale = 17)
	public double getWeightpu() {
		return this.weightpu;
	}

	public void setWeightpu(double weightpu) {
		this.weightpu = weightpu;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "mdd", nullable = false, length = 13)
	public Date getMdd() {
		return this.mdd;
	}

	public void setMdd(Date mdd) {
		this.mdd = mdd;
	}

	@Column(name = "pricepu", nullable = false, scale = 0)
	public double getPricepu() {
		return this.pricepu;
	}

	public void setPricepu(double pricepu) {
		this.pricepu = pricepu;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	public Set<OutgoingArticle> getOutgoingArticles() {
		return this.outgoingArticles;
	}

	public void setOutgoingArticles(Set<OutgoingArticle> outgoingArticles) {
		this.outgoingArticles = outgoingArticles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	public Set<IncomingArticle> getIncomingArticles() {
		return this.incomingArticles;
	}

	public void setIncomingArticles(Set<IncomingArticle> incomingArticles) {
		this.incomingArticles = incomingArticles;
	}

}
