package com.epam.objectdetection.tensorflow;


import org.tensorflow.Tensor;
import org.tensorflow.types.UInt8;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;

public class ImageTensorDataFactory {

	public static Tensor<UInt8> createImageTensor(BufferedImage img) {
		byte[] data = ((DataBufferByte) img.getData().getDataBuffer()).getData();
		final long BATCH_SIZE = 1;
		final long CHANNELS = 3;
		long[] shape = new long[]{BATCH_SIZE, img.getHeight(), img.getWidth(), CHANNELS};
		return Tensor.create(UInt8.class, shape, ByteBuffer.wrap(data));
	}
}
