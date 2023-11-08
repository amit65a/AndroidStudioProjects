import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

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
    var time = DateTime.now();
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Container(
        width: 400,
        height: 800,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              "${time.hour}:${time.minute}:${time.second}",
              style: TextStyle(fontSize: 20),
            ),
            ElevatedButton(
                onPressed: () {
                  setState(() {});
                },
                child: Text("Click")),
            Container(
              height: 40,
            ),
            Text("${DateFormat('jms').format(time)}",
                style: TextStyle(fontSize: 20)),
            Container(
              height: 30,
            ),
            Text("Select Date"),
            ElevatedButton(
                onPressed: () async {
                  DateTime? datePicked = await showDatePicker(
                      context: context,
                      initialDate: DateTime.now(),
                      firstDate: DateTime(2021),
                      lastDate: DateTime(2040));
                  if (datePicked != null) {
                    print(
                        "Date picked is : ${datePicked.year}:${datePicked.month}:${datePicked.day}");
                  }
                },
                child: Text("Show")),
            Container(
              height: 20,
            ),
            Text("Time Picker"),
            ElevatedButton(
                onPressed: () async {
                  TimeOfDay? selectedTime = await showTimePicker(
                      context: context,
                      initialTime: TimeOfDay.now(),
                      initialEntryMode: TimePickerEntryMode.input);
                  if (selectedTime != null) {
                    print(
                        "Time is : ${selectedTime.hour}:${selectedTime.minute}");
                  }
                },
                child: Text("Select"))
          ],
        ),
      ),
    );
  }
}
