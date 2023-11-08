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
  var userText = TextEditingController();
  var passwordText = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Container(
            padding: EdgeInsets.only(left: 15, right: 15),
            // width: 300,
            child: Column(
              children: [
                TextField(
                  enabled: true,
                  controller: userText,
                  decoration: InputDecoration(
                    hintText: "username",
                    prefixIcon: Icon(
                      Icons.account_circle,
                      color: Colors.deepOrange,
                    ),
                    suffixIcon: IconButton(
                      icon: Icon(
                        Icons.remove_red_eye,
                        color: Colors.deepOrange,
                      ),
                      onPressed: () {},
                    ),
                    focusedBorder: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(20),
                      borderSide: BorderSide(
                          color: Colors.deepOrangeAccent, width: 1.5),
                    ),
                    enabledBorder: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(20),
                        borderSide:
                            BorderSide(color: Colors.blueGrey, width: 1.5)),
                    border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(20)),
                  ),
                ),
                Container(
                  height: 20,
                ),
                TextField(
                  obscureText: true,
                  obscuringCharacter: "*",
                  keyboardType: TextInputType.number,
                  controller: passwordText,
                  decoration: InputDecoration(
                    prefixIcon: Icon(Icons.lock,color: Colors.deepOrange,),
                    hintText: "password",
                      focusedBorder: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(20),
                        borderSide: BorderSide(
                            color: Colors.deepOrangeAccent, width: 1.5),
                      ),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(20),
                          borderSide:
                              BorderSide(color: Colors.blueGrey, width: 1.5)),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(20))),
                ),
              ],
            ),
          ),
          InkWell(
            child: Card(
                elevation: 5,
                shadowColor: Colors.orange,
                child: Padding(
                  padding: const EdgeInsets.all(10.0),
                  child: Text("Hello"),
                )),
            onTap: () {
              String utext = userText.text.toString();
              String ptext = passwordText.text.toString();
              print("Username => $utext  Password => $ptext");
            },
          ),
        ],
      ),
    );
  }
}
