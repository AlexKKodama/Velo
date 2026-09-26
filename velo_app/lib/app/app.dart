import 'package:flutter/material.dart';
import 'package:velo_app/features/home/presentation/screens/home_screen.dart';

class VeloApp extends StatelessWidget{
  const VeloApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const HomeScreen(),
    );
  }
}
