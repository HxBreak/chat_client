import 'package:flutter/material.dart';
import 'package:chat/android/channels.dart';

class SettingScreen extends StatefulWidget {
  @override
  _SettingScreenState createState() => _SettingScreenState();
}

class _SettingScreenState extends State<SettingScreen> {
  final ip = TextEditingController();
  dynamic result;
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: <Widget>[
        Row(
          children: <Widget>[
            Text("地址"),
            Flexible(
              child: TextField(controller: ip),
            ),
          ],
        ),
        FlatButton(
          child: Text("连接"),
          onPressed: () async {
            result = await AndroidChannels.methodChannel
                .invokeMethod("connect", {"ip": ip.text});
          },
        ),
        Text("Result: $result")
      ],
    );
  }
}
