package de.carsten_lenz.liquibasegui;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.osgi.Db4oService;

import de.carsten_lenz.liquibasegui.model.RunConfiguration;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.carsten_lenz.liquibasegui";

	// The shared instance
	private static Activator plugin;
	
	private ObjectContainer db;

    private ServiceTracker db4oServiceTracker;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		db4oServiceTracker = new ServiceTracker(context, Db4oService.class.getName(), null);
		db4oServiceTracker.open();
		
		IPath stateLocation = this.getStateLocation();
		IPath dbFilePath = stateLocation.append("configurations.db4o");
		Db4oService db4oService = getDb4oService();
		db = db4oService.openFile(dbFilePath.toOSString());
		
		RunConfiguration test = new RunConfiguration();
		test.setConfigurationName("Test");
		db.store(test);
		db.commit();
	}

    private Db4oService getDb4oService() {
        return (Db4oService) db4oServiceTracker.getService();
    }

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	    db.close();
	    
	    db4oServiceTracker.close();
	    
		plugin = null;
		super.stop(context);
	}
	
	public List<RunConfiguration> getRunConfigurations() {
	    return db.query(RunConfiguration.class);
	}
	
	public void saveChanges(RunConfiguration runConfiguration) {
	    db.store(runConfiguration);
	    db.commit();
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
