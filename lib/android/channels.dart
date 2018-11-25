import 'package:flutter/services.dart';

class AndroidChannels {
  static MethodChannel methodChannel =
      MethodChannel("com.noname.backend/method");
  static EventChannel _eventChannel = EventChannel("com.noname.backend/event");
  static Stream _save_event_channel;
  static Stream get eventChannel {
    if (_save_event_channel == null) {
      _save_event_channel = _eventChannel.receiveBroadcastStream();
    }
    return _save_event_channel;
  }
}
