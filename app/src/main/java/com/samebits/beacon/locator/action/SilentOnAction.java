/*
 *
 *  Copyright (c) 2016 SameBits UG. All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.samebits.beacon.locator.action;

import android.content.Context;
import android.media.AudioManager;

import com.samebits.beacon.locator.util.PreferencesUtil;

/**
 * Created by vitas on 03/01/16.
 */
public class SilentOnAction extends Action {

    protected int mRingerMode;

    public SilentOnAction(String param) {
        super(param);
    }

    @Override
    public void execute(Context context) {
        final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
//                | AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_ALLOW_RINGER_MODES);
//        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            mRingerMode = AudioManager.RINGER_MODE_NORMAL;
        } else if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {
            audioManager.setMode(AudioManager.RINGER_MODE_SILENT);
            mRingerMode = AudioManager.RINGER_MODE_VIBRATE;
        }
        PreferencesUtil.setSilentModeProfile(context, mRingerMode);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    @Override
    public String toString() {
        return "SilentOnAction, param: "+param;
    }
}