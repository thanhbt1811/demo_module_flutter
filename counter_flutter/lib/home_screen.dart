import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Home Screen"),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: const [
              // ElevatedButton(
              //   onPressed: () {},
              //   child: const Text("Back"),
              // ),
              // ElevatedButton(
              //   onPressed: () {},
              //   child: const Text("Next"),
              // ),
              // ElevatedButton(
              //   onPressed: () {
              //     // Navigator.pushNamed(context, '/');
              //     Navigator.pop(context);
              //   },
              //   child: const Text("Go to init route"),
              // ),
            ],
          ),
        ));
  }
}
