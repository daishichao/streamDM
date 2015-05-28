/*
 * Copyright (C) 2015 Holmes Team at HUAWEI Noah's Ark Lab.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.spark.streamdm.core

import scala.collection.mutable.Map

/**
 * An InstanceSpecification contains information about the features.
 * It returns information of features that are not numeric,
 * and the names of all features, numeric and discrete,
 */

class InstanceSpecification extends Serializable {
  val featureSpecificationMap = Map[Int, FeatureSpecification]()
  val nameMap = Map[Int, String]()

  /** Gets the FeatureSpecification value present at position index
    *
    * @param index the index of the position
    * @return a FeatureSpecification representing the specification for the feature
    */
  def apply(index: Int): FeatureSpecification = featureSpecificationMap(index)


  /** Adds a specification for the instance feature
    *
    * @param index the index at which the value is added
    * @param input the feature specification which is added up
    */
  def setFeatureSpecification(index: Int, input: FeatureSpecification): Unit =
    featureSpecificationMap += (index -> input)


  /** Gets if the feature is nominal or discrete
    *
    * @param index the index of the feature
    * @return true if the feature is discrete
    */
  def isNominal(index:Int):Boolean =
    featureSpecificationMap.contains(index)

  /** Gets if the feature is numeric
    *
    * @param index the index of the feature
    * @return true if the feature is numeric
    */
  def isNumeric(index:Int):Boolean =
    !isNominal(index)

  /** Gets the name of the feature at position index
    *
    * @param index the index of the class
    * @return a string representing the name of the feature
    */
  def name(index: Int): String = nameMap(index)

  /** Adds a name for the instance feature
    *
    * @param index the index at which the value is added
    * @param input the feature name which is added up
    */
  def setName(index: Int, input: String): Unit =
    nameMap += (index -> input)

  /** Gets the number of features
    *
    * @return the number of features
    */
  def size(): Int = nameMap.size
}

/**
 * A FeatureSpecification contains information about its nominal values.
 *
 */

class FeatureSpecification(nominalValues:Array[String]) extends Serializable {
  val values = nominalValues
  val nameMap = Map[String,Int]()
  values.zipWithIndex.map{ case (element, index) => (nameMap += (element -> index)) }

  /** Get the nominal string value present at position index
    *
    * @param index the index of the feature value
    * @return a string containing the nominal value of the feature
    */
  def apply(index: Int): String =  {
    values(index)
  }

  /** Get the position index given the nominal string value
    *
    * @param string a string containing the nominal value of the feature
    * @return the index of the feature value
    */
  def apply(string: String): Int =  {
    nameMap(string)
  }
}
