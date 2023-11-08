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
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
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

    var arrNames=['Raman','Ritesh','Gopal','Sandesh','Jeevan','Rajesh'];

    return Scaffold(
      appBar: AppBar(

        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body:
      ListView.separated(itemBuilder: (context, index) {

        return ListTile(
          leading: Text("$index"),
          title: Text(arrNames[index]),
          subtitle: Text("Number"),
          trailing:Icon(Icons.add) ,
        );
      },
        itemCount:arrNames.length,
        separatorBuilder: (context,index){
          return Divider(height: 30,thickness: 2,color: Colors.red,);
        },
      ),



      // ListView.separated(itemBuilder: (context, index) {
      //
      //   return Text(arrNames[index],style: TextStyle(fontSize: 21,fontWeight: FontWeight.w500),);
      // },
      // itemCount:arrNames.length,
      //   separatorBuilder: (context,index){
      //   return Divider(height: 30,thickness: 2,color: Colors.red,);
      //   },
      // ),

                  // listView.separated or listView.builder once at a time only

      // ListView.builder(itemBuilder: (context, index) {
      //
      //   return Text(arrNames[index],style: TextStyle(fontSize: 21,fontWeight: FontWeight.w500),);
      // },
      //   itemCount:arrNames.length,
      //   scrollDirection: Axis.vertical,
      // ),

    );
  }
}
