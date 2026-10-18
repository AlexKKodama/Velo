import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:velo_app/features/home/presentation/widgets/destination_search_bar.dart';

import 'package:velo_app/features/map/presentation/widgets/app_map.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({
    super.key,
    required this.initialCenter,
  });

  final LatLng initialCenter;

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final MapController _mapController;

  @override
  void initState() {
    super.initState();

    _mapController = MapController();
  }

  @override
  void dispose() {
    _mapController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final options = MapOptions(
      initialCenter: widget.initialCenter,
      initialZoom: 15,
    );
    return Scaffold(
      body: Stack(
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
      ),
    );
  }
}
