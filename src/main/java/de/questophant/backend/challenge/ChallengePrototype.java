package de.questophant.backend.challenge;

import java.time.Instant;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import de.questophant.backend.validation.NoHtml;
import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class ChallengePrototype {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@ApiModelProperty(required = false, notes = "field is read-only")
	@Column(columnDefinition = "BINARY(16)")
	protected UUID createdByPublicUserId;

	// This is how it should be done but there is a bug in Hibernate for 7 years, which prevents it from working
	//@Formula("(SELECT u.user_name FROM users u WHERE u.id = 1)")
	@JsonInclude()
	@Transient
	@ApiModelProperty(required = false, notes = "field is read-only")
	protected String createdByUserName;

	@NotNull
	@Size(min = 2, max = 255)
	@NoHtml
	@ApiModelProperty(required = true, allowEmptyValue = false, allowableValues = "2-255 characters, no HTML", example = "This is a demonstration challenge.")
	protected String title;

	@Column(columnDefinition = "VARCHAR(255)")
	@ApiModelProperty(required = false, notes = "field is read-only, it contains the URL of the image to be displayed")
	protected String imageUrl;

	@NotNull
	@NoHtml
	@Column(columnDefinition = "VARCHAR(2048)")
	@Size(min = 10, max = 2048)
	@ApiModelProperty(required = true, allowEmptyValue = false, allowableValues = "10-2048 characters, no HTML", example = "This is a demonstration challenge.")
	protected String description;

	@Nullable
	//@Min(10)  
	@ApiModelProperty(required = false, allowableValues = "null, -1 (negative value means as long as possible), range[10,ininity] (bounds are not checked yet but duration < 10 makes no sense)")
	protected Long durationSeconds;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(16)")
	@ApiModelProperty(required = true, allowEmptyValue = false)
	protected Category category;

	@Nullable
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(16)")
	@ApiModelProperty(required = false, allowEmptyValue = false)
	protected ChallengeKind kind;

	@ApiModelProperty(required = false, notes = "field is read-only")
	protected Instant createdAt;

	@ApiModelProperty(required = false, notes = "field is read-only")
	protected Instant deletedAt;

	@ApiModelProperty(required = false, notes = "field is read-only")
	protected boolean createdByImport;

	@JsonIgnore
	protected boolean inDistribution;

	@Nullable
	@Min(1)
	@ApiModelProperty(required = false, allowableValues = "null, range[1,infinity]")
	protected Integer repeatableAfterDays;

	@Nullable
	@Size(min = 0, max = 255)
	@NoHtml
	@Column(columnDefinition = "VARCHAR(255)")
	@ApiModelProperty(required = false, allowEmptyValue = true, allowableValues = "null, 0-255 characters, no HTML", example = "scissor,knife")
	protected String material;

	@Min(0)
	@Max(100)
	@ApiModelProperty(required = false, allowableValues = "range[0,100]")
	protected int pointsWin;

	@Min(0)
	@Max(100)
	@ApiModelProperty(required = false, allowableValues = "range[0,100]")
	protected int pointsLoose;

	@ApiModelProperty(required = false, example = "false")
	protected boolean addToTreasureChest;

	public int getPointsWin() {
		return pointsWin;
	}
	public void setPointsWin(int pointsWin) {
		this.pointsWin = pointsWin;
	}
	public int getPointsLoose() {
		return pointsLoose;
	}
	public void setPointsLoose(int pointsLoose) {
		this.pointsLoose = pointsLoose;
	}
	public boolean isAddToTreasureChest() {
		return addToTreasureChest;
	}
	public void setAddToTreasureChest(boolean addToTreasureChest) {
		this.addToTreasureChest = addToTreasureChest;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getRepeatableAfterDays() {
		return repeatableAfterDays;
	}
	public void setRepeatableAfterDays(Integer repeatableAfterDays) {
		this.repeatableAfterDays = repeatableAfterDays;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ChallengeKind getKind() {
		return kind;
	}
	public void setKind(ChallengeKind kind) {
		this.kind = kind;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getCreatedByPublicUserId() {
		return createdByPublicUserId;
	}
	public void setCreatedByPublicUserId(UUID createdByPublicUserId) {
		this.createdByPublicUserId = createdByPublicUserId;
	}
	public Long getDurationSeconds() {
		return durationSeconds;
	}
	public void setDurationSeconds(Long durationSeconds) {
		this.durationSeconds = durationSeconds;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Instant deletedAt) {
		this.deletedAt = deletedAt;
	}
	public boolean isCreatedByImport() {
		return createdByImport;
	}
	public void setCreatedByImport(boolean createdByImport) {
		this.createdByImport = createdByImport;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String pictureUrl) {
		this.imageUrl = pictureUrl;
	}
	public boolean isInDistribution() {
		return inDistribution;
	}
	public void setInDistribution(boolean inDistribution) {
		this.inDistribution = inDistribution;
	}

}
