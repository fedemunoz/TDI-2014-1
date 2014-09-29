#include "FaceOsc.h"

void FaceOsc::sendFaceOsc(ofxFaceTracker& tracker) {
    clearBundle();

    if(tracker.getFound()) {
        addMessage("/found", 1);

        ofVec2f position = tracker.getPosition();
        addMessage("/pose/position", position);
        addMessage("/pose/scale", tracker.getScale());
        ofVec3f orientation = tracker.getOrientation();
        addMessage("/pose/orientation", orientation);
        //leon
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::MOUTH_HEIGHT),"boca");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::RIGHT_EYEBROW_HEIGHT),"cejaD");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::RIGHT_EYEBROW_HEIGHT),"altaD");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::LEFT_EYEBROW_HEIGHT),"cejaI");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::LEFT_EYEBROW_HEIGHT),"altaI");

        //panda
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::MOUTH_HEIGHT),"boquita");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::RIGHT_EYEBROW_HEIGHT),"orejaD");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::LEFT_EYEBROW_HEIGHT),"orejaI");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::RIGHT_EYE_OPENNESS),"ojoD");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::LEFT_EYE_OPENNESS),"ojoI");

        addMessage("/gesture/mouth/width", tracker.getGesture(ofxFaceTracker::MOUTH_WIDTH));
        //addMessage("/gesture/mouth/height", tracker.getGesture(ofxFaceTracker::MOUTH_HEIGHT));
        addMessage("/gesture/eyebrow/left", tracker.getGesture(ofxFaceTracker::LEFT_EYEBROW_HEIGHT));
        addMessage("/gesture/eyebrow/right", tracker.getGesture(ofxFaceTracker::RIGHT_EYEBROW_HEIGHT));
        addMessage("/gesture/eye/left", tracker.getGesture(ofxFaceTracker::LEFT_EYE_OPENNESS));
        addMessage("/gesture/eye/right", tracker.getGesture(ofxFaceTracker::RIGHT_EYE_OPENNESS));
        addMessage("/gesture/jaw", tracker.getGesture(ofxFaceTracker::JAW_OPENNESS));
        addMessage("/gesture/nostrils", tracker.getGesture(ofxFaceTracker::NOSTRIL_FLARE));
    } else {
        addMessage("/found", 0);
    }

    sendBundle();
}

void FaceOsc::clearBundle() {
	bundle.clear();
}

void FaceOsc::addMessage(string address, ofVec3f data) {
	ofxOscMessage msg;
	msg.setAddress(address);
	msg.addFloatArg(data.x);
	msg.addFloatArg(data.y);
	msg.addFloatArg(data.z);
	bundle.addMessage(msg);
}

void FaceOsc::addMessage(string address, ofVec2f data) {
	ofxOscMessage msg;
	msg.setAddress(address);
	msg.addFloatArg(data.x);
	msg.addFloatArg(data.y);
	bundle.addMessage(msg);
}

void FaceOsc::addMessage(string address, float data, string joint) {
	ofxOscMessage msg;
	msg.setAddress(address);
	msg.addStringArg(joint);
	float x=0; float y=0;
	if(joint=="boca"){
        x= data+2773.77;
        y= (data*35)+2256.43;
	}
	if(joint=="cejaI"){
        x= 2609.72+data;
        y= 1300 - (data*15);
	}
	if(joint=="cejaD"){
	    x= 2916.04-data;
        y= 1300- (data*15);
	}
	if(joint=="altaD"){
	    x= 3113.22-data;
        y= 1600- (data*70);
	}
	if(joint=="altaI"){
	    x= 2394.99+data;
        y= 1600- (data*70);
	}
	if(joint=="nariz"){
	    x= 2781.97;
        y= 1600 + (data*30);
	}

	//panda
	if(joint=="boquita"){
	    x= 1715;
        y= 1770 + (data*20);
	}
	if(joint=="ojoI"){
	    x= 1540;
	    if(data>2.9){
            y= 1185;
	    }else{
            y=1326;
	    }
	}
	if(joint=="ojoD"){
	    x= 1897;
        if(data>2.9){
            y= 1185;
	    }else{
            y=1326;
	    }
	}
	if(joint=="orejaD"){
	    x= 1900 + (data*10);
        y= 1100- (data*30);
	}
	if(joint=="orejaI"){
	    x= 1518.31 - (data*10);
        y= 1100- (data*30);
	}
	msg.addFloatArg(x);
	msg.addFloatArg(y);
	bundle.addMessage(msg);
}

void FaceOsc::addMessage(string address, int data) {
	ofxOscMessage msg;
	msg.setAddress(address);
	msg.addIntArg(data);
	bundle.addMessage(msg);
}

void FaceOsc::sendBundle() {
	osc.sendBundle(bundle);
}
