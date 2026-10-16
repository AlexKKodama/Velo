import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:geolocator/geolocator.dart';
import 'package:latlong2/latlong.dart';
import 'package:velo_app/features/home/presentation/widgets/destination_search_bar.dart';

import 'package:velo_app/features/map/presentation/widgets/app_map.dart';
import 'package:velo_app/features/locationService/location_service.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final MapController _mapController;
  late final Future<Position> _locationFuture;

  @override
  void initState() {
    super.initState();

    _mapController = MapController();
    _locationFuture = getCurrentLocation();
  }

  @override
  void dispose() {
    _mapController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder<Position>(
        future: _locationFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(
                child: CircularProgressIndicator(),
            );
          }

          if (snapshot.hasError) {
            return Center(
              child: Text('Error: ${snapshot.error}'),
            );
          }

          if (!snapshot.hasData) {
            return const Center(
                child: Text('Could not get location'),
            );
          }

          final position = snapshot.data!;

          final options = MapOptions(
            initialCenter: LatLng(
              position.latitude,
              position.longitude
            ),
            initialZoom: 15,
          );

          return Stack(
            children: [
              AppMap(
                controller: _mapController,
                options: options,
              ),
              SafeArea(
                child: Padding(
                  padding: const EdgeInsets.all(16),
                  child: Align(
                    alignment: Alignment.topCenter,
                    child: DestinationSearchBar(
                      onTap: () {

                      },
                    ),
                  ),
                ),
              ),
            ],
          );
        },
      ),
    );
  }
}
