package com.epam.objectdetection.tensorflow;

import java.util.ArrayList;
import java.util.List;

import org.tensorflow.Tensor;

public class TensorOutputProcessor {

	public static List<DetectedObject> getObjects(List<Tensor<?>> tensorOutput) {
		Tensor<Float> classesT = tensorOutput.get(0).expect(Float.class);
		Tensor<Float> scoresT = tensorOutput.get(1).expect(Float.class);
		Tensor<Float> boxesT = tensorOutput.get(2).expect(Float.class);

		int countObjects = (int) scoresT.shape()[1];
		float[] scores = scoresT.copyTo(new float[1][countObjects])[0];
		float[] classes = classesT.copyTo(new float[1][countObjects])[0];
		float[][] coords = boxesT.copyTo(new float[1][countObjects][4])[0];


		List<DetectedObject> liDetectedObjects = new ArrayList<>();
		for (int i = 0; i < scores.length; ++i) {
			ObjectBox objectCoord = new ObjectBox(coords[i][1], coords[i][3], coords[i][0], coords[i][2]);
			DetectedObject detectedObject = new DetectedObject((int) classes[i], scores[i], objectCoord);
			liDetectedObjects.add(detectedObject);
		}


		return liDetectedObjects;
	}

}
