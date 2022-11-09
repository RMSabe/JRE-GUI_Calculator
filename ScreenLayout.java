/*
 * ScreenLayout is a blank LayoutManager.
 * ScreenLayout is technically useless.
 * The Layout in this application is done using pixel coordinates.
 * ScreenLayout object is used only to group container and components together.
 */

import java.awt.*;

public class ScreenLayout implements LayoutManager
{
	@Override
	public void addLayoutComponent(String name, Component comp) {}

	@Override
	public void removeLayoutComponent(Component comp) {}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return null;
	}

	@Override
	public void layoutContainer(Container parent) {}
}
