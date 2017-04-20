import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class Practice extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>Hello, World</Text>
      </View>
    )
  }
}

class Fragment extends React.Component {
    render(){
        return(
            <View style={styles.container}>
                    <Text style={styles.hello}>This is RN Fragment</Text>
              </View>
        );
    }
}


var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

AppRegistry.registerComponent('Practice', () => Practice);
AppRegistry.registerComponent('Fragment', () => Fragment);