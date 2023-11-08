import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

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
    var arrColors = [
      Colors.red,
      Colors.orange,
      Colors.blue,
      Colors.pink,
      Colors.green,
      Colors.purpleAccent,
      Colors.brown
    ];
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text(widget.title),
        ),
        body: SingleChildScrollView(
          child: Column(
            children: [
              Container(
                height: 200,
                child: GridView.count(
                  crossAxisSpacing: 10,
                  mainAxisSpacing: 10,
                  crossAxisCount: 5,
                  children: [
                    Container(color: arrColors[0]),
                    Container(color: arrColors[1]),
                    Container(color: arrColors[2]),
                    Container(color: arrColors[3]),
                    Container(color: arrColors[4]),
                    Container(color: arrColors[5]),
                    Container(color: arrColors[6]),
                  ],
                ),
              ),
              Container(
                height: 100,
              ),
              Container(
                height: 400,
                child: GridView.extent(
                  maxCrossAxisExtent: 200,
                  crossAxisSpacing: 10,
                  mainAxisSpacing: 10,
                  children: [
                    Container(color: arrColors[0]),
                    Container(color: arrColors[1]),
                    Container(color: arrColors[2]),
                    Container(color: arrColors[3]),
                    Container(color: arrColors[4]),
                    Container(color: arrColors[5]),
                    Container(color: arrColors[6]),
                  ],
                ),
              ),
              Container(
                height: 100,
              ),
              Container(
                height: 300,
                child: GridView.builder(
                  gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
                    maxCrossAxisExtent: 200,
                    crossAxisSpacing: 10,
                    mainAxisSpacing: 10,
                  ),
                  itemBuilder: (context, index) {
                    return Container(color: arrColors[index]);
                  },
                  itemCount: arrColors.length,
                ),
              )
            ],
          ),
        ));
  }
}
