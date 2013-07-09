/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.drive.appdatapreferences;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Initializes an {@code AppdataSyncerAdapter} instance and manages its
 * life cycle.
 *
 * @author jbd@google.com (Burcu Dogan)
 */
public class AppdataSyncerService extends Service {

  private static AppdataSyncerAdapter sAppdataSyncerAdapter;

  /**
   * Initializes a new sync adapter on creation.
   */
  @Override
  public void onCreate() {
    synchronized (lock) {
      if (sAppdataSyncerAdapter == null) {
        sAppdataSyncerAdapter =
            new AppdataSyncerAdapter(getApplicationContext(), false);
      }
    }
  }

  @Override
  public IBinder onBind(Intent arg) {
    return sAppdataSyncerAdapter.getSyncAdapterBinder();
  }

  final static private Object lock = new Object();

}
