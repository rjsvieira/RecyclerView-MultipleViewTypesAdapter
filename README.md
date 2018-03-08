# RecyclerView-MultipleViewTypeAdapter

[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

RecyclerView adapter classes for managing multiple view types

 ![Sample](/sample/sample.gif)

# Gradle
```
repositories {
    jcenter()
}

dependencies {
    compile 'com.rjsvieira:recyclerview-multiple-viewtypes-adapter:1.0.0'
}
```

# Fork

This library stands as a fork of Yoshihito Ikeda's original (remarkable) repository.
This fork contains additional changes and fixes and may not entirely represent the original.
The package name was altered in order to prevent library clashing on bintray/jitpack integrations.


# About this library

The standard way to handle view creation and data binding logics for RecyclerView is to define the single adapter class extending RecyclerView.Adapter.  
If you need to handle simple view type, you will not get in trouble.  
But if you need to handle the multiple complex view types, writing logic in single adapter class causes unmaintainable source code.  
So, this library separates the logic for each view type into single class (DataBinder), and change the role of adapter class (DataBindAdapter) to manage these DataBinder classes.  
By using this library, the implementation would be simple and clear, and also source code for each view type would be maintenable and reusable.  


# Usage

### 1. Create DataBinder
Define class extending DataBinder for each view type.  
DataBinder class is used to bind view and data.  

Please refer the [binder samples](/sample/src/main/java/com/yqritc/recyclerviewmultipleviewtypesadapter/sample/binder) for usage.

### 2. Create DataBindAdapter
Create adapter class to manage DataBinder classes.  
Use (or extend) ListBindAdapter (or EnumListBindAdapter) if the order of view types used for recyclerview is in sequence.
If the order of view types is complex, create class by extending EnumMapBindAdapter or DataBindAdapter.  

Please refer the [adapter samples](/sample/src/main/java/com/yqritc/recyclerviewmultipleviewtypesadapter/sample/adapter) for usage.

### 3. Set adapter for recyclerview


# License
```
Copyright 2015 yqritc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
