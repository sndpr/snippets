package org.psc.misc.snippets;

import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
public class DefaultPojo {
	private String name;
	private BigInteger id;
	private List<String> infos;
}
