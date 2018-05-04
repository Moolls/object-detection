package com.epam.objectdetection.tensorflow;

import com.google.protobuf.TextFormat;
import com.epam.objectdetection.protos.StringIntLabelMapOuterClass;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class LabelsLoader {

	public String[] loadLabels(String filename) throws IOException {
		try {
			String text = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			StringIntLabelMapOuterClass.StringIntLabelMap.Builder builder = StringIntLabelMapOuterClass.StringIntLabelMap.newBuilder();
			TextFormat.merge(text, builder);
			StringIntLabelMapOuterClass.StringIntLabelMap proto = builder.build();
			int maxId = 0;
			for (StringIntLabelMapOuterClass.StringIntLabelMapItem item : proto.getItemList()) {
				if (item.getId() > maxId) maxId = item.getId();
			}
			String[] ret = new String[maxId + 1];
			for (StringIntLabelMapOuterClass.StringIntLabelMapItem item : proto.getItemList()) {
				ret[item.getId()] = item.getDisplayName();
			}
			return ret;
		} catch (TextFormat.ParseException e) {
			throw new IOException("File format is wrong.");
		} catch (IOException e) {
			throw new IOException("The file could not be read.");
		}
	}

}

