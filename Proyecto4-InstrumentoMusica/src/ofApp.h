#pragma once

#include "ofMain.h"
#include "ofxMidi.h"

class ofApp : public ofBaseApp{

	public:
		void setup();
		void update();
		void draw();
        void exit();

		void keyPressed(int key);
		void keyReleased(int key);
		void mouseMoved(int x, int y );
		void mouseDragged(int x, int y, int button);
		void mousePressed(int x, int y, int button);
		void mouseReleased(int x, int y, int button);
		void windowResized(int w, int h);
		void dragEvent(ofDragInfo dragInfo);
		void gotMessage(ofMessage msg);

        ofImage bikers;
        ofImage baja;
        ofImage pulgar;
        ofImage dedos;

        ofImage baja_nota;

        ofImage pulgar_nota;

        ofImage dedos_nota;

        ofxMidiOut midiOut;
        int channel;

        unsigned int currentPgm;
        int note, velocity;
        int pan, bend, touch, polytouch;
        string nota;
        bool keyIsDown[6];

};
