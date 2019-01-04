/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.googlejavaformat.java;

import com.google.errorprone.annotations.Immutable;

/**
 * Options for a google-java-format invocation.
 *
 * <p>Like gofmt, the google-java-format CLI exposes <em>no</em> configuration options (aside from {@code --aosp}).
 *
 * <p>The goal of google-java-format is to provide consistent formatting, and to free developers from arguments over
 * style choices. It is an explicit non-goal to support developers' individual preferences, and in fact it would work
 * directly against our primary goals.
 */
@Immutable
public class JavaFormatterOptions {

  public enum Style {

    /**
     * The default Google Java Style configuration.
     */
    GOOGLE(1, 100, true, true, false, true, false, 1),

    /**
     * The AOSP-compliant configuration.
     */
    AOSP(2, 100, true, true, false, true, false, 1),

    ATACCAMA(2, 140, false, false, true, false, true, 2);

    private final int indentationMultiplier;
    private final int maxLineLength;
    private final boolean wrapLineComments;
    private final boolean singleLineJavaDoc;
    private final boolean indentLambdaStatementAsBlock;
    private final boolean leadingBlankBlock;
    private final boolean nullAnnotations;
    private final int maxPreserveBlanks;

    Style(
            int indentationMultiplier,
            int maxLineLength,
            boolean wrapLineComments,
            boolean singleLineJavaDoc,
            boolean indentLambdaStatementAsBlock,
            boolean leadingBlankBlock,
            boolean nullAnnotations,
            int maxPreserveBlanks
    ) {
      this.indentationMultiplier = indentationMultiplier;
      this.maxLineLength = maxLineLength;
      this.wrapLineComments = wrapLineComments;
      this.singleLineJavaDoc = singleLineJavaDoc;
      this.indentLambdaStatementAsBlock = indentLambdaStatementAsBlock;
      this.leadingBlankBlock = leadingBlankBlock;
      this.nullAnnotations = nullAnnotations;
      this.maxPreserveBlanks = maxPreserveBlanks;
    }
  }

  private final Style style;

  private JavaFormatterOptions(Style style) {
    this.style = style;
  }

  /** Returns the multiplier for the unit of indent. */
  public int indentationMultiplier() {
    return style.indentationMultiplier;
  }

  public int maxLineLength() {
    return style.maxLineLength;
  }

  public boolean wrapLineComments() {
    return style.wrapLineComments;
  }

  public boolean singleLineJavaDoc() {
    return style.singleLineJavaDoc;
  }

  public boolean indentLambdaStatementAsBlock() {
    return style.indentLambdaStatementAsBlock;
  }

  public boolean leadingBlankBlock() {
    return style.leadingBlankBlock;
  }

  public boolean nullAnnotations() {
    return style.nullAnnotations;
  }

  public int maxPreserveBlanks() {
    return style.maxPreserveBlanks;
  }

  /** Returns the code style. */
  public Style style() {
    return style;
  }

  /**
   * Returns the default formatting options.
   */
  public static JavaFormatterOptions defaultOptions() {
    return builder().build();
  }

  /** Returns a builder for {@link JavaFormatterOptions}. */
  public static Builder builder() {
    return new Builder();
  }

  /** A builder for {@link JavaFormatterOptions}. */
  public static class Builder {
    private Style style = Style.GOOGLE;

    private Builder() {}

    public Builder style(Style style) {
      this.style = style;
      return this;
    }

    public JavaFormatterOptions build() {
      return new JavaFormatterOptions(style);
    }
  }
}
