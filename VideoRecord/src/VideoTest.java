import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_FOURCC;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvDestroyWindow;
import static com.googlecode.javacv.cpp.opencv_highgui.cvNamedWindow;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvReleaseCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;

import com.googlecode.javacv.FrameRecorder;
import com.googlecode.javacv.FrameRecorder.Exception;
import com.googlecode.javacv.OpenCVFrameRecorder;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

 class VideoTest {  
	 
   public static void main(String[] args) {
	   
	   try {
		   CvCapture capture = cvCreateCameraCapture(CV_CAP_ANY);
		   
		   opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 960);
	       opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 1280);
	     
		   cvNamedWindow("LiveVid", CV_WINDOW_AUTOSIZE);
		   
		   FrameRecorder recorder1 = new OpenCVFrameRecorder("RecordVid.avi", 1280, 960);
		   recorder1.setVideoCodec(CV_FOURCC('M','J','P','G'));
		   recorder1.setFrameRate(24);
		   recorder1.setPixelFormat(1);
		   recorder1.start();
		   
		   IplImage img1;
		   
		   for (;;) {
			
			   img1 = cvQueryFrame(capture);
			   
			   if(img1==null) break;
			   
			   cvShowImage("LiveVid", img1);
			   recorder1.record(img1);
			   
			   char c = (char) cvWaitKey(15);
			   if(c == 'q') break;
			   
		   }
		   
		   recorder1.stop();
		   cvDestroyWindow("LiveVid");
		   cvReleaseCapture(capture);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
   } 
   
 }  