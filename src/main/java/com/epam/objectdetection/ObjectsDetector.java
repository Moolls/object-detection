package com.epam.objectdetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Session.Runner;
import org.tensorflow.Tensor;
import org.tensorflow.types.UInt8;

import com.epam.objectdetection.tensorflow.DetectedObject;
import com.epam.objectdetection.tensorflow.ImageTensorDataFactory;
import com.epam.objectdetection.tensorflow.LabelsLoader;
import com.epam.objectdetection.tensorflow.TensorOutputProcessor;

public class ObjectsDetector {

	private static final String MODEL_PATH = "models/ssd_inception_v2_coco_2017_11_17/saved_model";
	private static final String LABEL_PATH = "models/ssd_inception_v2_coco_2017_11_17/labels/mscoco_label_map.pbtxt";
	private static final String IMG_PATH = "image.jpg";

	public static void main(String args[]) throws IOException {

		SavedModelBundle savedModelBundle = SavedModelBundle.load(MODEL_PATH, "serve");

		LabelsLoader labelsLoader = new LabelsLoader();
		String[] labels = labelsLoader.loadLabels(LABEL_PATH);

		BufferedImage img = ImageIO.read(new File(IMG_PATH));
		Tensor<UInt8> inputImage = ImageTensorDataFactory.createImageTensor(img);

		Session tensorFlowSession = savedModelBundle.session();
		Runner tensorFlowRunner = tensorFlowSession.runner();


		tensorFlowRunner = tensorFlowRunner.feed("image_tensor", inputImage);

		tensorFlowRunner = tensorFlowRunner.fetch("detection_classes");
		tensorFlowRunner = tensorFlowRunner.fetch("detection_scores");
		tensorFlowRunner = tensorFlowRunner.fetch("detection_boxes");

		List<Tensor<?>> tensorOutput = tensorFlowRunner.run();

		List<DetectedObject> detectedObjectList = TensorOutputProcessor.getObjects(tensorOutput);

		for (DetectedObject detectedObject : detectedObjectList) {
			System.out.println(detectedObject);
		}

		

	}

}
