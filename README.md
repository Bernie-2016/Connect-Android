# Connect for Android

Mobile application for getting involved and keeping up with Bernie.  

[![Circle CI](https://circleci.com/gh/Bernie-2016/Connect-Android.svg?style=svg)](https://circleci.com/gh/Bernie-2016/Connect-Android)

## Quick Links

Tracker: https://www.pivotaltracker.com/n/projects/1414762

Design: TODO

# Getting Started with Development

1. Use the gradle wrapper. When opening the project in Android Studio, be sure to use the gradle wrapper.
2. How ever if not using the Android Studio (your own IDE) install the stand alone SDK tools
3. You'll want to follow instructions to to export to path
4. You did it right if you did android in command line and it opens up something. In this tool select the Tools folder
the most recent SDK and the Extras folder then hit install
5. When running the gradle wrapper if there is a fail in the build run `tools/android update sdk --no-ui`
in your sdk folder.

# Contributing

## Making changes

In order to make changes, you need to get your development environment set up, make your changes on a fork of the Connect repository and then issue a pull request.  Please follow the advice below to ensure that your contribution will be readily accepted.

### Make your changes

* Create a branch for your changes (optional, but highly encouraged)
* Write tests for your changes
* Make your tests pass by implementing your changes
* Ensure _all_ tests still pass
* Push your changes up to your fork at github

### Make a pull request

When you have finished making your changes and testing them, go to your forked repo on github and issue a pull request for the branch containing your changes.  Please keep in mind the following before submitting your pull request:

* We favor pull requests with very small, single commits with a single purpose.  If you have many changes, they'll be more readily received as separate pull requests
* Please include tests.  They help us understand your intent and prevent future changes from breaking your work.  Changes without tests are usually ignored
* Please ensure all tests pass before making a pull request.  Your contribution shouldn't break the app for other users
* Please ensure all trailing whitespace characters are removed from your changes.
