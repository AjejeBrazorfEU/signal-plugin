# signal-plugin

Simple Ionic plugin to get the signal strength(LTE,WiFi,Acoustic).

## Disclaimer

The plugin only works for Android and has not been completely tested, use it carefully!!

## Install

```bash
npm install signal-plugin
npx cap sync
```

## Example

The function getSignalInfo() return an object containing the value of the 3 measured signals:
- `WIFI`
- `LTE`
- `MICROPHONE`

```TypeScript
async getSignalInfo(){
    this.signalInfo = (await SignalPlugin.getSignalInfo()).value;
    console.log("WIFI: " + this.signalInfo.WIFI);
    console.log("LTE: " + this.signalInfo.LTE);
    console.log("MICROPHONE: " + this.signalInfo.MICROPHONE);
}
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getSignalInfo()`](#getsignalinfo)
* [`checkPermissions()`](#checkpermissions)
* [`requestPermissions()`](#requestpermissions)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getSignalInfo()

```typescript
getSignalInfo() => Promise<{ value: any; }>
```

**Returns:** <code>Promise&lt;{ value: any; }&gt;</code>

--------------------


### checkPermissions()

```typescript
checkPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### requestPermissions()

```typescript
requestPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### Interfaces


#### PermissionStatus

| Prop             | Type                                                        |
| ---------------- | ----------------------------------------------------------- |
| **`WIFI`**       | <code><a href="#permissionstate">PermissionState</a></code> |
| **`LTE`**        | <code><a href="#permissionstate">PermissionState</a></code> |
| **`MICROPHONE`** | <code><a href="#permissionstate">PermissionState</a></code> |


### Type Aliases


#### PermissionState

<code>'prompt' | 'prompt-with-rationale' | 'granted' | 'denied'</code>

</docgen-api>
