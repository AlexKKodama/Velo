import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';

class AppMap extends StatelessWidget {
  const AppMap({
    super.key,
    required this.controller,
    this.options = const MapOptions(),
    this.children = const[]
  });

  final MapController controller;
  final MapOptions options;
  final List<Widget> children;

  @override
  Widget build(BuildContext context) {
    return FlutterMap(
      mapController: controller,
      options: options,
      children: [
        TileLayer(
          urlTemplate: '',
          userAgentPackageName: 'com.velo.app'
        ),
        ...children,
      ]
    );
  }
}
