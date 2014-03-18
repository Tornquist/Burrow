package com.petronicarts.burrow;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPoolPlayer {
    private SoundPool mShortPlayer= null;
    private HashMap mSounds = new HashMap();
    private  AudioManager  mAudioManager;
    private int stopID;
    public SoundPoolPlayer(Context pContext)
    {
        // setup Soundpool
        this.mShortPlayer = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        
        int soundID = this.mShortPlayer.load(pContext, R.raw.start, 1);
        mSounds.put(R.raw.start, soundID);
        mSounds.put(R.raw.bird, this.mShortPlayer.load(pContext, R.raw.bird, 1));
        mSounds.put(R.raw.carrot, this.mShortPlayer.load(pContext, R.raw.carrot, 1));
        mSounds.put(R.raw.die, this.mShortPlayer.load(pContext, R.raw.die, 1));
        mSounds.put(R.raw.silence, this.mShortPlayer.load(pContext, R.raw.silence, 1));
        mSounds.put(R.raw.hop, this.mShortPlayer.load(pContext,  R.raw.hop, 1));
        mAudioManager = (AudioManager)pContext.getSystemService(Context.AUDIO_SERVICE); 	   
    }

    public void playShortResource(int piResource) {
        int iSoundId = (Integer) mSounds.get(piResource);
        int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
        this.mShortPlayer.play(iSoundId, streamVolume, streamVolume, 1, 0, 1);
    }
    
    public void playSilence() {
        int iSoundId = R.raw.silence;
        int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        stopID = this.mShortPlayer.play(iSoundId, streamVolume, streamVolume, 0, -1, 1);
    }
    
    public void stopSilence() {
        this.mShortPlayer.stop(stopID);
    }
    

    // Cleanup
    public void release() {
        // Cleanup
        this.mShortPlayer.release();
        this.mShortPlayer = null;
    }
}