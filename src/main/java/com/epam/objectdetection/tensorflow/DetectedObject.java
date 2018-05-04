package com.epam.objectdetection.tensorflow;

public class DetectedObject {
	private int idLabel;
	private double score;
	private ObjectBox objectBox;

	public DetectedObject(int idLabel, double score, ObjectBox objectBox) {
		this.idLabel = idLabel;
		this.score = score;
		this.objectBox = objectBox;
	}

	public int getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(int idLabel) {
		this.idLabel = idLabel;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ObjectBox getObjectBox() {
		return objectBox;
	}

	public void setObjectBox(ObjectBox objectBox) {
		this.objectBox = objectBox;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("DetectedObject{");
		sb.append("idLabel='").append(idLabel).append('\'');
		sb.append(", score=").append(score);
		sb.append(", objectBox=").append(objectBox);
		sb.append('}');
		return sb.toString();
	}
}
