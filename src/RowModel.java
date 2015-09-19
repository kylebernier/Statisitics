import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

// TODO: Auto-generated Javadoc
/**
 * The Class RowModel.
 */
public class RowModel implements ListModel {

	/** The headers. */
	List<String> headers = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return headers.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public String getElementAt(int i) {
		return headers.get(i);
	}

	/**
	 * Adds the element.
	 */
	public void addElement() {
		headers.add(headers.size() + 1 + "");
	}

	/**
	 * Removes the element.
	 *
	 * @param i
	 *            the i
	 */
	public void removeElement(int i) {
		headers.remove(i);
	}

	/**
	 * Rename element.
	 *
	 * @param i
	 *            the i
	 * @param s
	 *            the s
	 */
	public void renameElement(int i, String s) {
		headers.set(i, s);
	}

	// Unused
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener
	 * )
	 */
	@Override
	public void addListDataListener(ListDataListener arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.
	 * ListDataListener)
	 */
	@Override
	public void removeListDataListener(ListDataListener arg0) {
	}
}