package com.synthbyte.scanmate.ui.screens.home

import androidx.compose.ui.draw.shadow

import androidx.compose.runtime.remember

import androidx.compose.runtime.mutableStateOf

import androidx.compose.foundation.layout.scale

import androidx.compose.animation.core.spring

import androidx.compose.animation.core.animateFloatAsState

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

class HomeNavItem(val label: String, val icon: ImageVector, val onClick: () -> Unit)

@Composable
fun HomeBottomNavigation(selected: String, onScan: () -> Unit, items: List<HomeNavItem>) {
    Box(modifier = Modifier.fillMaxWidth()) {
        NavigationBar(containerColor = MaterialTheme.colorScheme.surface, tonalElevation = 0.dp) {
            items.take(2).forEach { item ->
                NavigationBarItem(
                    selected = selected == item.label,
                    onClick = item.onClick,
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) }
                )
            }
            Spacer(modifier = Modifier.width(68.dp))
            items.drop(2).take(2).forEach { item ->
                NavigationBarItem(
                    selected = selected == item.label,
                    onClick = item.onClick,
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) }
                )
            }
        }
        
var pressed by remember { mutableStateOf(false) }
val scale by animateFloatAsState(if(pressed) 0.92f else 1f, label = "fab-scale", animationSpec = spring())
FloatingActionButton(
    onClick = {
        pressed = true
        onScan()
        pressed = false
    },
    modifier = Modifier
        .align(Alignment.TopCenter)
        .offset(y = (-22).dp)
        .size(54.dp)
        .scale(scale)
        .shadow(8.dp, RoundedCornerShape(16.dp)),
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
    shape = RoundedCornerShape(16.dp)
) {
    Icon(Icons.Default.CameraAlt, contentDescription = "Scan")
}

    }
}
