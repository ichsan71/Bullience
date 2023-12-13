package com.hanung.bullience.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.hanung.bullience.navigation.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)