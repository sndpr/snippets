package org.psc.misc.snippets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigInteger;
import java.util.List;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class DefaultPojo {
    private String name;
    private BigInteger id;
    private List<String> infos;
}
