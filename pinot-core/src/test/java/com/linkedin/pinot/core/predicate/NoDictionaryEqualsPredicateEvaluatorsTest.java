/**
 * Copyright (C) 2014-2016 LinkedIn Corp. (pinot-core@linkedin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedin.pinot.core.predicate;

import com.linkedin.pinot.common.data.FieldSpec;
import com.linkedin.pinot.core.common.predicate.EqPredicate;
import com.linkedin.pinot.core.common.predicate.NEqPredicate;
import com.linkedin.pinot.core.operator.filter.predicate.EqualsPredicateEvaluatorFactory;
import com.linkedin.pinot.core.operator.filter.predicate.NotEqualsPredicateEvaluatorFactory;
import com.linkedin.pinot.core.operator.filter.predicate.PredicateEvaluator;
import java.util.Collections;
import java.util.Random;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Unit test for no-dictionary based Eq and NEq predicate evaluators.
 */
public class NoDictionaryEqualsPredicateEvaluatorsTest {

  private static final String COLUMN_NAME = "column";
  private static final int NUM_MULTI_VALUES = 100;
  private static final int MAX_STRING_LENGTH = 100;
  private Random _random;

  @BeforeClass
  public void setup() {
    _random = new Random();
  }

  @Test
  public void testIntPredicateEvaluators() {
    int intValue = _random.nextInt();
    EqPredicate eqPredicate = new EqPredicate(COLUMN_NAME, Collections.singletonList(Integer.toString(intValue)));
    PredicateEvaluator eqPredicateEvaluator =
        EqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(eqPredicate, FieldSpec.DataType.INT);

    NEqPredicate neqPredicate = new NEqPredicate(COLUMN_NAME, Collections.singletonList(Integer.toString(intValue)));
    PredicateEvaluator neqPredicateEvaluator =
        NotEqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(neqPredicate, FieldSpec.DataType.INT);

    Assert.assertTrue(eqPredicateEvaluator.apply(intValue));
    Assert.assertFalse(neqPredicateEvaluator.apply(intValue));

    int[] randomInts = new int[NUM_MULTI_VALUES];
    PredicateEvaluatorTestUtils.fillRandom(randomInts);
    randomInts[_random.nextInt(randomInts.length)] = intValue;

    Assert.assertTrue(eqPredicateEvaluator.apply(randomInts));
    Assert.assertFalse(neqPredicateEvaluator.apply(randomInts));

    for (int i = 0; i < 100; i++) {
      int random = _random.nextInt();
      Assert.assertEquals(eqPredicateEvaluator.apply(random), (random == intValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(random), (random != intValue));

      PredicateEvaluatorTestUtils.fillRandom(randomInts);
      Assert.assertEquals(eqPredicateEvaluator.apply(randomInts), ArrayUtils.contains(randomInts, intValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(randomInts), !ArrayUtils.contains(randomInts, intValue));
    }
  }

  @Test
  public void testLongPredicateEvaluators() {
    long longValue = _random.nextLong();
    EqPredicate eqPredicate = new EqPredicate(COLUMN_NAME, Collections.singletonList(Long.toString(longValue)));
    PredicateEvaluator eqPredicateEvaluator =
        EqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(eqPredicate, FieldSpec.DataType.LONG);

    NEqPredicate neqPredicate = new NEqPredicate(COLUMN_NAME, Collections.singletonList(Long.toString(longValue)));
    PredicateEvaluator neqPredicateEvaluator =
        NotEqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(neqPredicate, FieldSpec.DataType.LONG);

    Assert.assertTrue(eqPredicateEvaluator.apply(longValue));
    Assert.assertFalse(neqPredicateEvaluator.apply(longValue));

    long[] randomLongs = new long[NUM_MULTI_VALUES];
    PredicateEvaluatorTestUtils.fillRandom(randomLongs);
    randomLongs[_random.nextInt(randomLongs.length)] = longValue;

    Assert.assertTrue(eqPredicateEvaluator.apply(randomLongs));
    Assert.assertFalse(neqPredicateEvaluator.apply(randomLongs));

    for (int i = 0; i < 100; i++) {
      long random = _random.nextLong();
      Assert.assertEquals(eqPredicateEvaluator.apply(random), (random == longValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(random), (random != longValue));

      PredicateEvaluatorTestUtils.fillRandom(randomLongs);
      Assert.assertEquals(eqPredicateEvaluator.apply(randomLongs), ArrayUtils.contains(randomLongs, longValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(randomLongs), !ArrayUtils.contains(randomLongs, longValue));
    }
  }

  @Test
  public void testFloatPredicateEvaluators() {
    // FLOAT data type
    float floatValue = _random.nextFloat();
    EqPredicate eqPredicate = new EqPredicate(COLUMN_NAME, Collections.singletonList(Float.toString(floatValue)));
    PredicateEvaluator eqPredicateEvaluator =
        EqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(eqPredicate, FieldSpec.DataType.FLOAT);

    NEqPredicate neqPredicate = new NEqPredicate(COLUMN_NAME, Collections.singletonList(Float.toString(floatValue)));
    PredicateEvaluator neqPredicateEvaluator =
        NotEqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(neqPredicate, FieldSpec.DataType.FLOAT);

    Assert.assertTrue(eqPredicateEvaluator.apply(floatValue));
    Assert.assertFalse(neqPredicateEvaluator.apply(floatValue));

    float[] randomFloats = new float[NUM_MULTI_VALUES];
    PredicateEvaluatorTestUtils.fillRandom(randomFloats);
    randomFloats[_random.nextInt(randomFloats.length)] = floatValue;

    Assert.assertTrue(eqPredicateEvaluator.apply(randomFloats));
    Assert.assertFalse(neqPredicateEvaluator.apply(randomFloats));

    for (int i = 0; i < 100; i++) {
      float random = _random.nextFloat();
      Assert.assertEquals(eqPredicateEvaluator.apply(random), (random == floatValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(random), (random != floatValue));

      PredicateEvaluatorTestUtils.fillRandom(randomFloats);
      Assert.assertEquals(eqPredicateEvaluator.apply(randomFloats), ArrayUtils.contains(randomFloats, floatValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(randomFloats), !ArrayUtils.contains(randomFloats, floatValue));
    }
  }

  @Test
  public void testDoublePredicateEvaluators() {
    double doubleValue = _random.nextDouble();
    EqPredicate eqPredicate = new EqPredicate(COLUMN_NAME, Collections.singletonList(Double.toString(doubleValue)));
    PredicateEvaluator eqPredicateEvaluator =
        EqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(eqPredicate, FieldSpec.DataType.DOUBLE);

    NEqPredicate neqPredicate = new NEqPredicate(COLUMN_NAME, Collections.singletonList(Double.toString(doubleValue)));
    PredicateEvaluator neqPredicateEvaluator =
        NotEqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(neqPredicate, FieldSpec.DataType.DOUBLE);

    Assert.assertTrue(eqPredicateEvaluator.apply(doubleValue));
    Assert.assertFalse(neqPredicateEvaluator.apply(doubleValue));

    double[] randomDoubles = new double[NUM_MULTI_VALUES];
    PredicateEvaluatorTestUtils.fillRandom(randomDoubles);
    randomDoubles[_random.nextInt(randomDoubles.length)] = doubleValue;

    Assert.assertTrue(eqPredicateEvaluator.apply(randomDoubles));
    Assert.assertFalse(neqPredicateEvaluator.apply(randomDoubles));

    for (int i = 0; i < 100; i++) {
      double random = _random.nextDouble();
      Assert.assertEquals(eqPredicateEvaluator.apply(random), (random == doubleValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(random), (random != doubleValue));

      PredicateEvaluatorTestUtils.fillRandom(randomDoubles);
      Assert.assertEquals(eqPredicateEvaluator.apply(randomDoubles), ArrayUtils.contains(randomDoubles, doubleValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(randomDoubles), !ArrayUtils.contains(randomDoubles, doubleValue));
    }
  }

  @Test
  public void testStringPredicateEvaluators() {
    String stringValue = RandomStringUtils.random(MAX_STRING_LENGTH);
    EqPredicate eqPredicate = new EqPredicate(COLUMN_NAME, Collections.singletonList(stringValue));
    PredicateEvaluator eqPredicateEvaluator =
        EqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(eqPredicate, FieldSpec.DataType.STRING);

    NEqPredicate neqPredicate = new NEqPredicate(COLUMN_NAME, Collections.singletonList(stringValue));
    PredicateEvaluator neqPredicateEvaluator =
        NotEqualsPredicateEvaluatorFactory.newNoDictionaryBasedEvaluator(neqPredicate, FieldSpec.DataType.STRING);

    Assert.assertTrue(eqPredicateEvaluator.apply(stringValue));
    Assert.assertFalse(neqPredicateEvaluator.apply(stringValue));

    String[] randomStrings = new String[NUM_MULTI_VALUES];
    PredicateEvaluatorTestUtils.fillRandom(randomStrings, MAX_STRING_LENGTH);
    randomStrings[_random.nextInt(randomStrings.length)] = stringValue;

    Assert.assertTrue(eqPredicateEvaluator.apply(randomStrings));
    Assert.assertFalse(neqPredicateEvaluator.apply(randomStrings));

    for (int i = 0; i < 100; i++) {
      String random = RandomStringUtils.random(MAX_STRING_LENGTH);
      Assert.assertEquals(eqPredicateEvaluator.apply(random), (random.equals(stringValue)));
      Assert.assertEquals(neqPredicateEvaluator.apply(random), (!random.equals(stringValue)));

      PredicateEvaluatorTestUtils.fillRandom(randomStrings, MAX_STRING_LENGTH);
      Assert.assertEquals(eqPredicateEvaluator.apply(randomStrings), ArrayUtils.contains(randomStrings, stringValue));
      Assert.assertEquals(neqPredicateEvaluator.apply(randomStrings), !ArrayUtils.contains(randomStrings, stringValue));
    }
  }
}
