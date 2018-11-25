import 'package:flutter/material.dart';
import 'screens.dart';

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int current = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text("ChatClient"),
      ),
      body: current == 0
          ? Container(color: Colors.blue)
          : current == 1 ? Container() : SettingScreen(),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: current,
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.home), title: Text("home")),
          BottomNavigationBarItem(icon: Icon(Icons.stars), title: Text("fav")),
          BottomNavigationBarItem(
              icon: Icon(Icons.flip_to_back), title: Text("test")),
        ],
        type: BottomNavigationBarType.fixed,
        onTap: (i) {
          setState(() {
            current = i;
          });
        },
      ),
    );
  }
}
