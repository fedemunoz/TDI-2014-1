#include "ofApp.h"
#include <math.h>

int xr,xa,xv,xy;
int yr,ya,yv,yy;
int width;
int height, actual_note;
int pelotitas[4][2]={{xr,yr},{xa,ya},{xv,yv},{xy,yy}};
float tiempo;
char array[20];
bool baja_izq, dedos_izq, pulgar_izq, baja_der, dedos_der, pulgar_der;
//{9,9,9,9,9,6,6,0,1,9,1,0,6,5,4,9,4,5,6,9,6,5,9,5}
int notas []= {9,9,9,9,9,6,6,0,1,9,1,0,6,5,4,9,4,5,6,9,6,5,9,5,6,9,6,0,1,9,1,0,6,5,4,9,4,5,6,5,4,9,4,5,9,5,6,4,5,6,0,6,4,5,6,0,6,5,4,5,1,9,1,6,9,6,0,1,9,1,0,6,5,4,9,4,5,6,5,4,4};
//--------------------------------------------------------------
 void ofApp::setup(){

    bikers.loadImage("images/hand.png");

    baja.loadImage("images/mano_baja.png");
    pulgar.loadImage("images/mano_pulgar.png");
    dedos.loadImage("images/mano_dedos.png");
    dedos.loadImage("images/mano_dedos.png");
    baja_nota.loadImage("images/nota_baja.png");
    pulgar_nota.loadImage("images/nota_pulgar.png");
    dedos_nota.loadImage("images/nota_dedos.png");
    baja_izq=false;
    dedos_izq=false;
    pulgar_izq=false;
    baja_der=false;
    dedos_der=false;
    pulgar_der=false;

    yr=0;
    ya=-100;
    yv=-200;
    yy=0;
    width= ofGetWindowWidth();
    height= ofGetWindowHeight();
    xr=187;
    xa=450;
    xv=184;
    xy=562;

    //midi
    ofSetVerticalSync(true);
    ofBackground(255);
    ofSetLogLevel(OF_LOG_VERBOSE);

    // print the available output ports to the console
    midiOut.listPorts(); // via instance
    //ofxMidiOut::listPorts(); // via static too

    // connect
    //midiOut.openPort(0); // by number
    midiOut.openPort("loopMIDI Port"); // by name
    //midiOut.openVirtualPort("loopMIDI Port"); // open a virtual port

    channel = 1;
    currentPgm = 0;
    note = 0;
    velocity = 0;
    pan = 0;
    bend = 0;
    touch = 0;
    polytouch = 0;
    nota="";


}

//--------------------------------------------------------------
void ofApp::update(){

    tiempo= ofGetElapsedTimef();

    int i = round(tiempo);

    actual_note = notas[i];


    velocity= 64;


//    yr+=1;
//    ya+=1;
//    yv+=1;
//    yy+=1;
//    if(yr>height-10){
//        yr=-10000;
//    }
//    if(ya>390){
//        ya=-10000;
//    }
//    if(yv>180){
//        yv=-10000;
//    }


}

//--------------------------------------------------------------
void ofApp::draw(){
    //fondo
    bikers.draw(120, 200);

 //notas de la cancion

    if(actual_note == 0){

    dedos_nota.draw(174, 215);

    dedos_nota.draw(225, 195);

    dedos_nota.draw(306, 220);

    }

    else if(actual_note == 1){

        baja_nota.draw(145, 439);

    }

    else if(actual_note == 5){

        baja_nota.draw(616, 439);

    }

    else if(actual_note == 4){

        pulgar_nota.draw(474, 362);

    }

    else if(actual_note == 6){

        dedos_nota.draw(567, 217);

        dedos_nota.draw(647, 193);

        dedos_nota.draw(698, 215);

    }

    //notas que toco yo
    if(baja_izq==true){
        ofSetCircleResolution(100);
        ofCircle(232,522,60);
        baja.draw(128, 423);
    }
    if(dedos_izq==true){
        dedos.draw(162, 199);
        dedos.draw(213, 179);
        dedos.draw(294, 204);
    }
    if(pulgar_izq==true){
        pulgar.draw(367, 348);
    }
    if(baja_der==true){
        baja.draw(599, 423);
    }
    if(dedos_der==true){
        dedos.draw(555, 201);
        dedos.draw(635, 177);
        dedos.draw(686, 199);
    }
    if(pulgar_der==true){
        pulgar.draw(457, 345);
    }
}

//--------------------------------------------------------------
void ofApp::exit() {

    // clean up
    midiOut.closePort();
}

//--------------------------------------------------------------
void ofApp::keyPressed(int key){

    velocity = 64;


    //right
    if(key==358){
        //    note = 60; //C
        if(!keyIsDown[0]){
            keyIsDown[0]= true;
        }

    }
    //up
    if(key==357){
        //  note = 63; //D#
        if(!keyIsDown[1]){
            keyIsDown[1]= true;
        }

    }
    //down
    if(key==359){
        //  note = 65; //F
        if(!keyIsDown[2]){
            keyIsDown[2]= true;
        }

    }
    //left
    if(key==356){
        //  note = 67; //G
        if(!keyIsDown[3]){
            keyIsDown[3]= true;
        }

    }

    //spacebar
    if(key==32){
        // note = 70; //A#
        if(!keyIsDown[4]){
            keyIsDown[4]= true;
        }
    }

    if(keyIsDown[0] and keyIsDown[1] ){
        // Up Right
        note=64; //E
        nota="E - Mi";
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[0] and keyIsDown[2]){
        //Right Down
        note=65; //F
        nota="F - Fa";
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[0] and keyIsDown[3]){
        //Right Left
        note=61; //C#
        nota="C# - DO#";
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[0] and keyIsDown[4]){
        //Right Spacebar
        note=63; //D#
        nota="D# - Re#";
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[1] and keyIsDown[2]){
        //Up Down
        note=68; //F#
        nota="F# - Fa#";
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[1] and keyIsDown[3]){
        //Up Left
        note=56; //G#
        nota="G# - Sol#";
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[1] and keyIsDown[4]){
        //Up Spacebar
        note=58; //A#
        nota="A# - La#";
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[0]){
        //Right
        note=60; //C
        nota="C - DO";
        //baja_der = true;
        dedos_izq = true;
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[1]){
        //Up
        note=62; //D
        nota="D - Re";
        //dedos_izq = true;
        baja_izq = true;
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[2]){
        //Down
        note=55; //G
        nota="G - Sol";
        pulgar_der = true;
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[3]){
        //Left
        note=57; //A
        nota="A - La";
        //dedos_der = true;
        baja_der = true;
        midiOut.sendNoteOn(channel, note,  velocity);
    }else if (keyIsDown[4]){
        //Spacebar
        note=59; //B
        nota="B - Si";
        //baja_izq = true;
        dedos_der = true;
        midiOut.sendNoteOn(channel, note,  velocity);
    }


    if(key == 'l') {
        ofxMidiOut::listPorts();
    }
}

//--------------------------------------------------------------
void ofApp::keyReleased(int key){

    for (note=53;note<=70;note++){
        midiOut.sendNoteOff(channel,note,velocity);
    }

    baja_izq=false;
    dedos_izq=false;
    pulgar_izq=false;
    baja_der=false;
    dedos_der=false;
    pulgar_der=false;

    //right
    if(key==358){
        keyIsDown[0]= false;
    }
    //up
    if(key==357){
        keyIsDown[1]= false;
    }
    //down
    if(key==359){
        keyIsDown[2]= false;
    }
    //left
    if(key==356){
        keyIsDown[3]=false;
    }
    //spacebar
    if(key==32){
        keyIsDown[4]= false;
    }

    switch(key) {
        case '[':
            touch = 64;
            midiOut.sendAftertouch(channel, touch);
            break;
        case ']':
            touch = 127;
            midiOut << Aftertouch(channel, touch); // stream interface
            break;

        case '<':
            polytouch = 64;
            midiOut.sendPolyAftertouch(channel, 64, polytouch);
            break;
        case '>':
            polytouch = 127;
            midiOut << PolyAftertouch(channel, 64, polytouch); // stream interface
            break;

        case 'S': {
            midiOut.sendMidiByte(MIDI_SYSEX);
            midiOut.sendMidiByte(0x47);	// akai manufacturer code
            midiOut.sendMidiByte(0x00); // channel 0
            midiOut.sendMidiByte(0x42); // MULTI
            midiOut.sendMidiByte(0x48); // using an Akai S2000
            midiOut.sendMidiByte(0x00); // Part 1
            midiOut.sendMidiByte(0x00);	// transpose
            midiOut.sendMidiByte(0x01); // Access Multi Parts
            midiOut.sendMidiByte(0x4B); // offset
            midiOut.sendMidiByte(0x00);	// offset
            midiOut.sendMidiByte(0x01); // Field size = 1
            midiOut.sendMidiByte(0x00); // Field size = 1
            midiOut.sendMidiByte(0x04); // pitch value = 4
            midiOut.sendMidiByte(0x00); // offset
            midiOut.sendMidiByte(MIDI_SYSEX_END);

            vector<unsigned char> sysexMsg;
            sysexMsg.push_back(MIDI_SYSEX);
            sysexMsg.push_back(0x47);
            sysexMsg.push_back(0x00);
            sysexMsg.push_back(0x42);
            sysexMsg.push_back(0x48);
            sysexMsg.push_back(0x00);
            sysexMsg.push_back(0x00);
            sysexMsg.push_back(0x01);
            sysexMsg.push_back(0x4B);
            sysexMsg.push_back(0x00);
            sysexMsg.push_back(0x01);
            sysexMsg.push_back(0x00);
            sysexMsg.push_back(0x04);
            sysexMsg.push_back(0x00);
            sysexMsg.push_back(MIDI_SYSEX_END);
            midiOut.sendMidiBytes(sysexMsg);

            midiOut << StartMidi() << MIDI_SYSEX
            << 0x47 << 0x00 << 0x42 << 0x48 << 0x00 << 0x00 << 0x01
            << 0x4B << 0x00 << 0x01 << 0x00 << 0x04 << 0x00
            << MIDI_SYSEX_END << FinishMidi();
            break;
        }
        case '?':
            midiOut.listPorts();
            break;

        case ' ':
            midiOut << StartMidi() << 0x80 << 0x3C << 0x40 << FinishMidi();
            break;

        default:
            break;
    }

}

//--------------------------------------------------------------
void ofApp::mouseMoved(int x, int y ){

}

//--------------------------------------------------------------
void ofApp::mouseDragged(int x, int y, int button){

}

//--------------------------------------------------------------
void ofApp::mousePressed(int x, int y, int button){

}

//--------------------------------------------------------------
void ofApp::mouseReleased(int x, int y, int button){

}

//--------------------------------------------------------------
void ofApp::windowResized(int w, int h){

}

//--------------------------------------------------------------
void ofApp::gotMessage(ofMessage msg){

}

//--------------------------------------------------------------
void ofApp::dragEvent(ofDragInfo dragInfo){

}
