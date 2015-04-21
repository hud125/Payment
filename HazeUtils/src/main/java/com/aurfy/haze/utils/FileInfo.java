package com.aurfy.haze.utils;

import java.util.Date;

/**
 * File information class.
 * 
 */
public final class FileInfo {

	private String id;
	private String name;
	private String suffix;
	private String relativePath;
	private String fullPath;
	private long size;
	private String mimeType;
	private Date createTime;
	private String comments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((relativePath == null) ? 0 : relativePath.hashCode());
		result = prime * result + ((fullPath == null) ? 0 : fullPath.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FileInfo)) {
			return false;
		}
		FileInfo other = (FileInfo) obj;
		if (comments == null) {
			if (other.comments != null) {
				return false;
			}
		} else if (!comments.equals(other.comments)) {
			return false;
		}
		if (createTime == null) {
			if (other.createTime != null) {
				return false;
			}
		} else if (!createTime.equals(other.createTime)) {
			return false;
		}
		if (relativePath == null) {
			if (other.relativePath != null) {
				return false;
			}
		} else if (!relativePath.equals(other.relativePath)) {
			return false;
		}
		if (fullPath == null) {
			if (other.fullPath != null) {
				return false;
			}
		} else if (!fullPath.equals(other.fullPath)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (mimeType == null) {
			if (other.mimeType != null) {
				return false;
			}
		} else if (!mimeType.equals(other.mimeType)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		if (suffix == null) {
			if (other.suffix != null) {
				return false;
			}
		} else if (!suffix.equals(other.suffix)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileInfo [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", relativePath=");
		builder.append(relativePath);
		builder.append(", fullPath=");
		builder.append(fullPath);
		builder.append(", size=");
		builder.append(size);
		builder.append(", mimeType=");
		builder.append(mimeType);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", suffix=");
		builder.append(suffix);
		builder.append(", comments=");
		builder.append(comments);
		builder.append("]");
		return builder.toString();
	}
}
