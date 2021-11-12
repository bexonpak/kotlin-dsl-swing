package org.dt.bexon.blg.ui.swing

import java.awt.*
import java.awt.event.ActionEvent
import java.net.URI
import javax.swing.*

typealias Width = Int
typealias Height = Int
typealias CoordinateX = Int
typealias CoordinateY = Int

inline fun JFrame.frame(title: String = "", init: JFrame.() -> Unit): JFrame = apply {
    this.title = title
    init()
}

inline fun JFrame.panel(init: JPanel.() -> Unit): JPanel = JPanel().apply {
    init()
}.also { contentPane = it }

inline fun JPanel.label(label: String = "", init: JLabel.() -> Unit): JLabel = JLabel(label).apply {
    init()
}.also { add(it) }

inline fun JPanel.button(text: String = "", init: JButton.() -> Unit): JButton = JButton(text).apply {
    init()
}.also { add(it) }

inline fun JPanel.link(text: String = "", uri: String = "", init: JButton.() -> Unit): JButton = JButton(text).apply {
    this.text = text
    this.setHorizontalAlignment(SwingConstants.LEFT)
    this.isBorderPainted = false
    this.isOpaque = false
    this.background = Color.WHITE
    this.toolTipText = uri
    this.addActionListener {
        try {
            Desktop.getDesktop().browse(URI(uri))
        } catch (e: Exception) {
        }
    }
    init()
}.also { add(it) }

inline fun MenuBar.menu(label: String = "", init: Menu.() -> Unit): Menu = Menu(label).apply {
    init()
}.also { add(it) }

inline fun menuBar(init: MenuBar.() -> Unit): MenuBar = MenuBar().apply {
    init()
}

inline fun Menu.menuItem(label: String, init: MenuItem.() -> Unit, noinline action: ((ActionEvent) -> Unit)): MenuItem =
    MenuItem(label).apply {
        this.label = label
        addActionListener(action)
        init()
    }.also { add(it) }

fun alert(frame: JFrame?, msg: String = "") {
    JOptionPane.showMessageDialog(frame, msg)
}

fun rectangle(x: CoordinateX = 0, y: CoordinateY = 0, dimension: Dimension): Rectangle =
    Rectangle(x, y, dimension.width, dimension.height)

infix fun Width.x(height: Height) = Dimension(this, height)