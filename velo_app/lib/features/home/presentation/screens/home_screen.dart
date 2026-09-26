import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:geolocator/geolocator.dart';
import 'package:latlong2/latlong.dart';
import 'package:velo_app/features/locationService/location_service.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final Future<Position> _locationFuture;

  @override
  void initState() {
    super.initState();

    _locationFuture = getCurrentLocation();
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Position>(
      future: _locationFuture,
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const Scaffold(
            body: Center(
              child: CircularProgressIndicator(),
            ),
          );
        }

        if (snapshot.hasError) {
          return Scaffold(
            body: Center(
              child: Text('Error: ${snapshot.error}'),
            ),
          );
        }

        if (!snapshot.hasData) {
          return const Scaffold(
            body: Center(
              child: Text('Could not get location'),
            ),
          );
        }

        final position = snapshot.data!;

        return Scaffold(
          body: FlutterMap(
            options: MapOptions(
              initialCenter: LatLng(
                position.latitude,
                position.longitude,
              ),
              initialZoom: 15,
            ),
            children: [
              TileLayer(
                urlTemplate: '',
                userAgentPackageName: 'com.velo.app',
              ),
            ],
          ),
        );
      },
    );
  }
}
