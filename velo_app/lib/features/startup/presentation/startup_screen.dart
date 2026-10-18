import 'dart:async';

import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';
import 'package:velo_app/features/home/presentation/screens/home_screen.dart';
import 'package:velo_app/features/locationService/location_service.dart';

class StartupScreen extends StatefulWidget {
  const StartupScreen({super.key});

  @override
  State<StartupScreen> createState() => _StartupScreenState();
}

class _StartupScreenState extends State<StartupScreen> {
  @override
  void initState() {
    super.initState();
    _initialize();
  }

  static const LatLng _fallbackLocation = LatLng(
    -23.5505,
    -46.6333,
  );

  Future<LatLng> _getInitialCenter() async {
    try {
      final position = await getCurrentLocation().timeout(
        const Duration(seconds: 3),
      );

      return LatLng(
        position.latitude,
        position.longitude,
      );
    } on TimeoutException {
      debugPrint('Location request timed out.');
      return _fallbackLocation;
    } catch (error, stackTrace) {
      debugPrint('Could not get location: $error');
      debugPrintStack(stackTrace: stackTrace);
      return _fallbackLocation;
    }
  }

  Future<void> _initialize() async {
    final initialCenter = await _getInitialCenter();

    if(!mounted) return;

    Navigator.pushReplacement(
      context,
      MaterialPageRoute(
        builder: (_) => HomeScreen(
          initialCenter: initialCenter,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            FlutterLogo(size: 80),
            SizedBox(height: 24),
            CircularProgressIndicator(),
          ],
        ),
      ),
    );
  }
}
