import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Container(
        margin: EdgeInsets.only(left: 11, right: 11),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Row(children: [
              Text(
                "Hello",
                style: TextStyle(fontSize: 30, fontWeight: FontWeight.w700),
              )
            ]),
            Row(
              children: [
                Expanded(
                  flex: 3,
                  child: Container(
                    width: 50,
                    height: 100,
                    color: Colors.blue,
                  ),
                ),
                Expanded(
                  flex: 4,
                  child: Container(
                    width: 50,
                    height: 100,
                    color: Colors.red,
                  ),
                ),
                Expanded(
                  flex: 3,
                  child: Container(
                    width: 50,
                    height: 100,
                    color: Colors.green,
                  ),
                ),
                Expanded(
                  child: Container(
                    width: 50,
                    height: 100,
                    color: Colors.grey,
                  ),
                ),
              ],
            ),
            Column(
              children: [
                Center(
                  child: CircleAvatar(
                    child: Container(
                      child: Column(
                        children: [
                          Padding(
                            padding: const EdgeInsets.only(top: 10),
                            child: CircleAvatar(
                              radius: 60,
                              backgroundImage:
                                  (AssetImage("assets/images/1.jpg")),
                            ),
                          ),
                          Text("name",style: TextStyle(fontFamily: "Nexa"),)
                        ],
                      ),
                    ),
                    backgroundColor: Colors.green,
                    maxRadius: 80,
                  ),
                )
              ],
            )
          ],
        ),
      ),
    );
  }
}
