# Object Detection in Java

Example of using pre-trained models of [TensorFlow Object Detection
API](https://github.com/tensorflow/models/tree/master/research/object_detection)
in Java.


## QUICK START

1. Download some metadata files (list of object labels and .proto files for mapping labels): 
   ```
   ./download.sh
   ```

2.  Download a pre-trained model from the [object detection API model zoo](https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/detection_model_zoo.md)
    and extract archive to /models OR Use model from this repository
    
    For example:
    ```
    mkdir -p models
    curl -L \
    http://download.tensorflow.org/models/object_detection/ssd_inception_v2_coco_2017_11_17.tar.gz \
    | tar -xz -C models/
    ```
    
    
3. Compile and run!
    
    

 