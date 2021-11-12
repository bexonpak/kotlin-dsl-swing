package org.dt.bexon.blg

import org.dt.bexon.blg.ui.swing.*
import java.awt.MenuShortcut
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.border.EmptyBorder

fun main() {
    MainView()
}

class MainView : JFrame() {
    init {
        System.setProperty("apple.awt.application.name", "Test App")
        System.setProperty("apple.laf.useScreenMenuBar", "true")
        frame(title = "JFrame with DSL") {
            defaultCloseOperation = EXIT_ON_CLOSE
            bounds = rectangle(dimension = 340 x 200)
            isVisible = true
            setLocationRelativeTo(null)
            menuBar = menuBar {
                menu("menu") {
                    menuItem("click me", {
                        shortcut = MenuShortcut(KeyEvent.VK_A, true)
                    }) {
                        alert(this@frame, "Thank you for using swing.")
                    }
                }
            }

            panel {
                bounds = rectangle(dimension = 200 x 400)
                border = EmptyBorder(5, 5, 5, 5)
                layout = null

                label("👆 Click it!") {
                    bounds = rectangle(10, 10, 146 x 14)
                }

                link("🌏 Open in github.", "https://github.com/bexonpak/kotlin-dsl-swing") {
                    bounds = rectangle(10, 100, 146 x 24)
                }
            }
        }
    }
}