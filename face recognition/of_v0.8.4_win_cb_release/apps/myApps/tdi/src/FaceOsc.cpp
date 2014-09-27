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
        //addMessage("/joint", tracker.getGesture(ofxFaceTracker::MOUTH_WIDTH),"fede");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::MOUTH_HEIGHT),"boca");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::RIGHT_EYEBROW_HEIGHT),"cejaD");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::RIGHT_EYEBROW_HEIGHT),"altaD");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::LEFT_EYEBROW_HEIGHT),"cejaI");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::LEFT_EYEBROW_HEIGHT),"altaI");
        addMessage("/joint", tracker.getGesture(ofxFaceTracker::JAW_OPENNESS),"nariz");

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
        y= (data*40)+2256.43;
	}
	if(joint=="cejaI"){
        x= data+2595.68;
        y= 1217.2 - (data*40);
	}
	if(joint=="cejaD"){
	    x= data+2931.85;
        y= 1231.58- (data*40);
	}
	if(joint=="altaD"){
	    x= data+3157.42;
        y= 1177.69- (data*50);
	}
	if(joint=="altaI"){
	    x= data+2234.19;
        y= 1175.85- (data*50);
	}
	if(joint=="nariz"){
	    x= 2782.02;
        y= 1807.62- (data*20);
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
