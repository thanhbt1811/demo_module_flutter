import 'package:counter_flutter/home_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      onGenerateRoute: (settings) {
        switch (settings.name) {
          case '/':
            return MaterialPageRoute(
              builder: (ctx) =>
                  const MyHomePage(title: 'Flutter Demo Home Page'),
            );
          case '/home':
            return MaterialPageRoute(
              builder: (ctx) => const HomeScreen(),
            );
        }
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  late MethodChannel _channel;

  @override
  void initState() {
    super.initState();
    _channel = const MethodChannel('counter_method');
    _channel.setMethodCallHandler((call) async {
      if (call.method == "setCounter") {
        // A notification that the host platform's data model has been updated.
        if (call.arguments != null) {
          setState(() {
            _counter = call.arguments as int;
          });
        }
      } else {
        throw Exception('not implemented ${call.method}');
      }
    });
  }

  Future<void> _incrementCounter() async {
    setState(() {
      _counter++;
      _channel.invokeMethod('incrementCounter', {"counter": _counter});
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
            ElevatedButton(
              onPressed: () => _channel.invokeMethod<void>('back'),
              child: const Text("Back"),
            ),
            ElevatedButton(
              onPressed: () => _channel.invokeMethod<void>('next'),
              child: const Text("Next"),
            ),
            ElevatedButton(
              onPressed: () => Navigator.of(context).pushNamed('/home'),
              child: const Text("Go Home"),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
