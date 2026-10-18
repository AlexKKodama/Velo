import 'package:flutter/material.dart';
import 'package:velo_app/features/startup/presentation/startup_screen.dart';

class VeloApp extends StatelessWidget{
  const VeloApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const StartupScreen(),
    );
  }
}
