package nom.tam.fits.test;

/*
 * #%L
 * nom.tam FITS library
 * %%
 * Copyright (C) 1996 - 2015 nom-tam-fits
 * %%
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * #L%
 */

import nom.tam.fits.Fits;
import nom.tam.fits.FitsFactory;
import nom.tam.fits.Header;

import org.junit.Test;

public class UserProvidedTest {

    @Test
    public void testRewriteableHierarchImageWithLongStrings() throws Exception {
        boolean longStringsEnabled = FitsFactory.isLongStringsEnabled();
        boolean useHierarch = FitsFactory.getUseHierarch();
        try {
            FitsFactory.setUseHierarch(true);
            FitsFactory.setLongStringsEnabled(true);

            String filename = "src/test/resources/nom/tam/image/provided/issue49test.fits";
            Fits fits = new Fits(filename);
            Header headerRewriter = fits.getHDU(0).getHeader();
            // the real test is if this throws an exception, it should not!
            headerRewriter.rewrite();
            fits.close();
        } finally {
            FitsFactory.setLongStringsEnabled(longStringsEnabled);
            FitsFactory.setUseHierarch(useHierarch);

        }
    }
}
