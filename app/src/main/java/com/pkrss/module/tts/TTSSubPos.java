package com.pkrss.module.tts;

import com.pkrss.module.TTSModule;

/**
 * Created by liand on 2016/4/21.
 */
public final class TTSSubPos {
    /**
     * playing text is skiped length
     */
    private static int skipedPos = 0;

    /**
     * playing progress percent in tts worker
     */
    private static int playingProgressPercent = 0;

    /**
     * full text
     */
    private static String text = "";

    /**
     * get speaking text
     * @return
     */
    public static String getText() {
        if(skipedPos == 0)
            return text;
        return text.substring(skipedPos);
    }

    public static int getProgressPercent() {
        int totalLength = text.length();
        if(totalLength == 0)
            return 0;
        int speakedLength = playingProgressPercent * (totalLength-skipedPos) / 100;
        int percent =  (skipedPos + speakedLength)*100 / totalLength;
        if(percent>100)
            return 100;
        return percent;
    }

    public static int setProgressPercent(int progressPercent) {
        playingProgressPercent = progressPercent;
        return getProgressPercent();
    }

    public static boolean play(String content, int skipedPos){
        boolean isPlaying = false;
        TTSModule.ITtsWorker ttsWorker = TTSModule.getInstance().getCurWorker();
        if(ttsWorker == null)
            return false;

        TTSSubPos.skipedPos = skipedPos;
        String speakingString = TTSSubPos.text;
        if(content == null)
            content = "";
        if(content == speakingString || content.equals(speakingString)) {
            if (ttsWorker.isSpeaking()) {
                ttsWorker.pause();
                isPlaying = false;
            } else {
                ttsWorker.resume();
                isPlaying = true;
            }
        }else{
            TTSSubPos.text = content;
            ttsWorker.play();
            isPlaying = true;
        }
        return isPlaying;
    }

    public static boolean playProgress(int percentProgress){
        boolean isPlaying = false;

        TTSModule.ITtsWorker ttsWorker = TTSModule.getInstance().getCurWorker();
        if(ttsWorker == null)
            return false;

        String speakingString = TTSSubPos.text;

        if(speakingString.length()==0)
            return false;

        int length = speakingString.length();

        skipedPos = length * percentProgress / 100;

        if(skipedPos>=length) {
            return false;
        }

        ttsWorker.stop();
        ttsWorker.play();

        return isPlaying;
    }
}
