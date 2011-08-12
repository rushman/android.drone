package vi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

//Copyright © 2007-2011, PARROT SA, all rights reserved. 

//DISCLAIMER 
//The APIs is provided by PARROT and contributors "AS IS" and any express or implied warranties, including, but not limited to, the implied warranties of merchantability 
//and fitness for a particular purpose are disclaimed. In no event shall PARROT and contributors be liable for any direct, indirect, incidental, special, exemplary, or 
//consequential damages (including, but not limited to, procurement of substitute goods or services; loss of use, data, or profits; or business interruption) however 
//caused and on any theory of liability, whether in contract, strict liability, or tort (including negligence or otherwise) arising in any way out of the use of this 
//software, even if advised of the possibility of such damage. 

//Author            : Daniel Schmidt
//Publishing date   : 2010-01-06 
//based on work by  : Wilke Jansoone

//Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions
//are met:
//- Redistributions of source code must retain the above copyright notice, this list of conditions, the disclaimer and the original author of the source code.
//- Neither the name of the PixVillage Team, nor the names of its contributors may be used to endorse or promote products derived from this software without 
//specific prior written permission.

public class uint {

	private int base2;

	public uint(final byte[] bp, final int start) {
		try {
			final byte[] b = new byte[4];
			b[0] = bp[start + 3];
			b[1] = bp[start + 2];
			b[2] = bp[start + 1];
			b[3] = bp[start + 0];

			final ByteArrayInputStream bas = new ByteArrayInputStream(b);
			final DataInputStream din = new DataInputStream(bas);

			this.base2 = din.readInt();
		} catch (final Exception e) {
			throw new RuntimeException("error creating uint", e);
		}
	}

	public uint(final int base) {
		this.base2 = base;

	}

	public uint(final uint that) {
		this.base2 = that.base2;
	}

	public uint and(final int andval) {
		final int retval = base2 & andval;
		return new uint(retval);
	}

	public int flipBits() {
		final int base = ~base2;

		return base;
	}

	public byte[] getBytes() {
		try {
			final ByteArrayOutputStream bas = new ByteArrayOutputStream();
			final DataOutputStream dout = new DataOutputStream(bas);

			dout.writeInt(base2);
			dout.close();

			final byte[] ba = bas.toByteArray();

			if (ba.length != 4) {
				throw new RuntimeException("somehow got " + ba.length
						+ " bytes instead of 4 bytes from int " + base2);
			}
			final byte[] b = new byte[4];
			b[0] = ba[3];
			b[1] = ba[2];
			b[2] = ba[1];
			b[3] = ba[0];

			return b;
		} catch (final Exception e) {
			throw new RuntimeException("error in uint getBytes", e);
		}
	}

	public int intValue() {
		return base2;

	}

	public uint or(final uint orval) {
		final int retval = base2 | orval.base2;
		return new uint(retval);
	}

	public uint shiftLeft(final int i) {
		int base = base2;
		base <<= i;

		return new uint(base);
		// return Integer.parseInt(base, 2);
	}

	public void shiftLeftEquals(final int i) {
		int base = base2;

		base <<= i;

		base2 = base;
	}

	public uint shiftRight(final int i) {
		// System.out.println("shiftRight[0] " + base2 + " " + i);

		// String str = Integer.toBinaryString(base);
		int base = base2;
		// System.out.println("shiftRight[n][1] " + uint.toBinaryString(base));

		base = base >>> i;

		// System.out.println("shiftRight[n][2] " + uint.toBinaryString(base));

		return new uint(base);
	}

	public void shiftRightEquals(final int i) {
		int base = base2;

		base >>>= i;

		base2 = base;
	}

	public short times(final short i) {
		return (short) (intValue() * i);
	}

	@Override
	public String toString() {
		return Integer.toString(base2, 2);
	}
}
