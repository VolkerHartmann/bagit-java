package gov.loc.repository.bagit.filesystem.filter;

import gov.loc.repository.bagit.filesystem.DirNode;
import gov.loc.repository.bagit.filesystem.FileSystemNode;
import gov.loc.repository.bagit.filesystem.FileSystemNodeFilter;
import gov.loc.repository.bagit.filesystem.impl.AbstractFileNode;

public class DirNodeFileSystemNodeFilter implements FileSystemNodeFilter {
	@Override
	public boolean accept(FileSystemNode fileSystemNode) {
		if (fileSystemNode.getFileSystem().getDefaultNodeFilter() != null &&
				fileSystemNode instanceof AbstractFileNode &&
				!fileSystemNode.getFileSystem().getDefaultNodeFilter().accept(fileSystemNode)) {
				// excluded by the default filter
				return false;
		}
		return fileSystemNode instanceof DirNode;
	}
}