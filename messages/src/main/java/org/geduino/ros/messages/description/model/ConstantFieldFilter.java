package org.geduino.ros.messages.description.model;

import org.geduino.ros.core.util.Filter;

class ConstantFieldFilter implements Filter<FieldDescription> {

	private final boolean flag;

	ConstantFieldFilter(boolean flag) {
		this.flag = flag;
	}

	@Override
	public boolean accept(FieldDescription fieldDescription) {
		return flag == fieldDescription.isConstant();
	}

}
